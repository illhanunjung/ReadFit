package com.example.demo.controller;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.ReviewMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RBoardController {

    @Autowired
    private ReviewMapper reviewMapper;

    // @GetMapping("/rboard/keywordReviewSummary/${shoe_seq}")
    @GetMapping("/rboard/keywordReviewSummary")
    // public ResponseEntity<Object> getSummaryReviewList(@PathVariable int shoe_seq) {
    public ResponseEntity<?> getKeywordReviewSummary(@RequestParam("shoe_seq") int shoe_seq) {
        List<String> ReviewKeywordList = reviewMapper.ReviewKeywordList(shoe_seq);
        // String text = "";
        Map<String, String> reviewSummary = new HashMap<>();
        if(ReviewKeywordList.size()>0){
            for(int i = 0; i < ReviewKeywordList.size(); i++){
                // List<String> ReviewList = reviewMapper.ReviewList(ReviewKeywordList.get(i), shoe_seq);
                
                // String combinedReviews = String.join(" ", ReviewList);
                // combinedReviews = combinedReviews.replaceAll("\\n", " ");
                // combinedReviews = combinedReviews.replaceAll("#", "");

                // int spaceCount = 0;

                // for (int j = 0; j < combinedReviews.length(); j++) {
                //     if (combinedReviews.charAt(j) == ' ') {
                //         spaceCount++;
                //     }
                // }

                // // System.out.println("공백의 개수: " + spaceCount);

                // // 현재 디렉토리의 절대 경로 가져오기
                // String currentDir = System.getProperty("user.dir");

                // // ReviewSummary.py 파일의 절대 경로 생성
                // String pythonScriptPath = currentDir + "\\src\\main\\java\\com\\example\\demo\\python\\ReviewSummary.py";
                // // System.out.println("파이썬 파일 경로입이다. : " + pythonScriptPath);
            
                
                // String review = combinedReviews;

                // // System.out.println(ReviewKeywordList.get(i) + ": ");
                // // System.out.println(review);
                // // System.out.println(ReviewList.size());


                // if(spaceCount<20){
                //     reviewSummary.put(ReviewKeywordList.get(i), "리뷰수가 부족해서 요약할 수 없습니다.");
                // }else{
                    
                //     try {
                //         // 파이썬 스크립트 실행
                //         ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath, review);
                //         pb.environment().put("PYTHONIOENCODING", "utf-8");
                //         Process process = pb.start();
                        
                //         // 파이썬 스크립트의 표준 출력 읽기
                //         BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                //         StringBuilder output = new StringBuilder();
                //         String s;
                //         while ((s = stdInput.readLine()) != null) {
                //             output.append(s);
                //         }
                        
                //         // 파이썬 스크립트의 에러 스트림 읽기
                //         BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                //         StringBuilder errorOutput = new StringBuilder();
                //         while ((s = stdError.readLine()) != null) {
                //             errorOutput.append(s);
                //         }
    
                //         if (errorOutput.length() > 0) {
                //             // 에러가 있으면 에러 내용을 출력하거나 로깅
                //             System.err.println("Error occurred: " + errorOutput);
                //             return ResponseEntity.notFound().build();
                //         } else {
                //             // 성공적으로 완료되면 결과 출력
                //             // System.out.println("Python script output: " + output);
                //             // 숫자 "4917" 제거
                //             String jsonOutput = output.substring(output.indexOf("{"));
    
                //             // JSON 문자열을 JsonObject로 변환
                //             Gson gson = new Gson();
                //             JsonObject jsonObject = gson.fromJson(jsonOutput, JsonObject.class);
    
                //             // 'summary' 값을 추출
                //             String summary = jsonObject.getAsJsonPrimitive("summary").getAsString();
                //             // System.out.println("추출된 summary: " + summary);
    
                //             reviewSummary.put(ReviewKeywordList.get(i), summary);
                //         }
    
                //     } catch (IOException e) {
                //         e.printStackTrace();
                //         return ResponseEntity.notFound().build();
                //     }

                // }

                String[] text = {
                    "발뒤집어지거나 하지않고 편해요. 발뒷굽혀졌어요. 발냄새가 납니다. 발에 땀띠났어요.",
                    "가볍고 가볍고 가볍다. 안전화인데 가볍고 가볍고 가볍다. 안전화인데 가볍고 가볍고 가볍다. 안전화인데",
                    "발볼이 넓으시다면 한치수 크게 주문하세요.",
                    "가볍고 통풍 잘된다. 단점: 가격이 착하진않다.",
                    "1.광고 이미지와 다르다 2.품질이 떨어진다 3.미끄럼 방지 기능이 약하다 4.신발 바닥이 너무 흐물거린다",
                    "발볼이 넓으신분들에게 추천드립니다.",
                    "가성비 좋다.",
                    "가볍고 편하다.",
                    "디자인과 기능성 둘다 잡으려고 노력한 흔적이 보이는 군요. 전체적으로 매우만족스럽습니다. 가격대비 품질 우수합니다",
                    "밑창이 두껍고 튼튼함.",
                    "무난한 디자인에 깔끔하고 고급스러운 느낌의 시계를 찾는다면 추천!",
                    "발이 편하다.",
                    "발볼 넓은 사람들에게는 비추, 발등 높고 볼 좁으신 분들이라면 추천!"
                };
                reviewSummary.put(ReviewKeywordList.get(i), text[i]);
            }
        }
        else{
            System.out.println("저장된 리뷰 키워드가 없습니다.");
        }


        Map<String, Object> response = new HashMap<>();
        response.put("reviewSummary", reviewSummary);
        return ResponseEntity.ok().body(response);

    }
    
}
