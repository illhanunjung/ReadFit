package com.example.demo.controller;

import com.example.demo.mapper.MemberMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mapper.MemberMapper;


@RestController
@RequestMapping("/api/img")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class ImageController {

    // 이미지를 저장할 경로 설정
    private final Path rootLocation = Paths.get("src/main/resources/static/img/uploads/profile");
    


    @Autowired
    private MemberMapper memberMapper;

    @PostMapping("/upload/profile")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("image") MultipartFile file,
                                                @RequestParam("mem_id") String memId) {
        try {
            // 파일명 생성 (시스템 시간을 앞에 붙여 중복 방지)
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path savePath = this.rootLocation.resolve(filename);

            // 디렉터리가 없다면 생성
            Files.createDirectories(savePath.getParent());

            // 파일 저장
            Files.copy(file.getInputStream(), savePath);

            System.out.println("Uploaded filename: " + filename);
            System.out.println("Saved path: " + savePath);
            System.out.println("id: " + memId);
            

            // DB에 저장할 이미지 경로 생성
            String imagePath = filename;

            // DB에 이미지 경로 업데이트
            memberMapper.updateProfileImage(memId, imagePath);

            // 업로드 성공 응답 반환
            return ResponseEntity.ok().body(filename);
        } catch (IOException e) {
            // 업로드 실패 시 예외 처리
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Could not upload the file: " + file.getOriginalFilename());
        }

    }


// 프로필 이미지 가져오기
@GetMapping("/profile/{mem_id}")
public ResponseEntity<?> getProfileImage(@PathVariable("mem_id") String memId) {
    try {
        // 데이터베이스에서 사용자의 프로필 이미지 경로를 가져옴
        String profileImagePath = memberMapper.getProfileImage(memId);
        if (profileImagePath != null) {
            // 이미지 파일로부터 Resource 객체 생성
            Path imagePath = rootLocation.resolve(profileImagePath);
            Resource resource = new UrlResource(imagePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                // 이미지를 성공적으로 읽어서 클라이언트에게 반환
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(resource);
            } else {
                // 이미지를 찾을 수 없음을 클라이언트에게 반환
                return ResponseEntity.notFound().build();
            }
        } else {
            // 프로필 이미지가 없을 경우 빈 문자열 반환
            return ResponseEntity.ok().body("");
        }
    } catch (Exception e) {
        // 에러 발생 시 클라이언트에게 예외 메시지 반환
        e.printStackTrace();
        return ResponseEntity.badRequest().body("에러 " + memId);
        
    }
}


}


