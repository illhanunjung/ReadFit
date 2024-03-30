package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;

@RestController
public class KakaoController {

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @PostMapping("/members/register")
    public ResponseEntity<?> kakaoLogin(@RequestBody HashMap<String, String> payload) {
        String code = payload.get("code");
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        
        String tokenRequestUri = "https://kauth.kakao.com/oauth/token";
        
        try {
            @SuppressWarnings("rawtypes")
            ResponseEntity<HashMap> response = restTemplate.postForEntity(tokenRequestUri, request, HashMap.class);
            @SuppressWarnings("unchecked")
            HashMap<String, Object> responseBody = response.getBody();
            if(responseBody != null && responseBody.containsKey("access_token")) {
                String accessToken = (String) responseBody.get("access_token");
                
                // 액세스 토큰을 사용하여 사용자 정보 가져오기
                return getUserInfo(accessToken);
            } else {
                return ResponseEntity.badRequest().body("Access token not found");
            }
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
    
    

    private ResponseEntity<?> getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        try {
            @SuppressWarnings("rawtypes")
            ResponseEntity<HashMap> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, entity, HashMap.class);
            return ResponseEntity.ok().body(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}
