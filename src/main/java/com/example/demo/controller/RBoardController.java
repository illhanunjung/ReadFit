package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @GetMapping("/rboard/keywordReviewSummary")
    public ResponseEntity<?> getKeywordReviewSummary(@RequestParam("shoe_seq") int shoe_seq) {
        Map<String, String> reviewSummary = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        System.out.println("슈 시퀀스 값입니다. : " + shoe_seq);
        List<String> ReviewKeywordList = reviewMapper.ReviewKeywordList(shoe_seq);
        Map<String, Object> reviewSummaryInTable = reviewMapper.searchReviewSummarybyshoeSeq(shoe_seq);
        if (reviewSummaryInTable != null) {
            // System.out.println("REVIEW_SUMMARY테이블에 요약된 정보가 들어 있습니다.");
            // System.out.println("review_summary_seq: " + reviewSummaryInTable.get("review_summary_seq"));
            // System.out.println("shoe_seq: " + reviewSummaryInTable.get("shoe_seq"));
            // System.out.println("summary_text: " + reviewSummaryInTable.get("summary_text"));
            // System.out.println("review_count: " + reviewSummaryInTable.get("review_count"));
            
            String reviewSummaryIn = (String)reviewSummaryInTable.get("summary_text");
            reviewSummaryIn = reviewSummaryIn.substring(1, reviewSummaryIn.length() - 1);
            String[] items = reviewSummaryIn.split(", ");
            for (String item : items) {
                String[] keyValue = item.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    reviewSummary.put(key, value);
                }
            }

            for (String key : reviewSummary.keySet()) {
                System.out.println(key + ": " + reviewSummary.get(key));
            }
        } else {
            System.out.println("여기 들어왔습니다.");
            String text = "";

            if (ReviewKeywordList.size() > 0) {
                for (int i = 0; i < ReviewKeywordList.size(); i++) {
                    List<String> ReviewList = reviewMapper.ReviewList(ReviewKeywordList.get(i), shoe_seq);

                    String combinedReviews = String.join(" ", ReviewList);
                    combinedReviews = combinedReviews.replaceAll("\\n", " ");
                    combinedReviews = combinedReviews.replaceAll("#", "");

                    int spaceCount = 0;
                    for (int j = 0; j < combinedReviews.length(); j++) {
                        if (combinedReviews.charAt(j) == ' ') {
                            spaceCount++;
                        }
                    }

                    System.out.println("공백의 개수: " + spaceCount);

                    String currentDir = System.getProperty("user.dir");
                    String pythonScriptPath = currentDir + "\\src\\main\\java\\com\\example\\demo\\python\\ReviewSummary.py";

                    // System.out.println("currentDir입니다 : " + currentDir);
                    // System.out.println("pythonScriptPath입니다1 : " + pythonScriptPath);
                    pythonScriptPath = pythonScriptPath.replace("ReadFit-frontend", "ReadFit");
                    // System.out.println("pythonScriptPath입니다2 : " + pythonScriptPath);

                    if (spaceCount < 20) {
                        reviewSummary.put(ReviewKeywordList.get(i), "리뷰수가 부족해서 요약할 수 없습니다.");
                    } else {
                        try {
                            ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath, combinedReviews);
                            pb.environment().put("PYTHONIOENCODING", "utf-8");
                            Process process = pb.start();

                            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                            StringBuilder output = new StringBuilder();
                            String s;
                            while ((s = stdInput.readLine()) != null) {
                                output.append(s);
                            }

                            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                            StringBuilder errorOutput = new StringBuilder();
                            while ((s = stdError.readLine()) != null) {
                                errorOutput.append(s);
                            }

                            if (errorOutput.length() > 0) {
                                System.err.println("Error occurred: " + errorOutput);
                                return ResponseEntity.notFound().build();
                            } else {
                                String jsonOutput = output.substring(output.indexOf("{"));
                                Gson gson = new Gson();
                                JsonObject jsonObject = gson.fromJson(jsonOutput, JsonObject.class);
                                if (jsonObject != null && jsonObject.has("summary")) {
                                    String summary = jsonObject.getAsJsonPrimitive("summary").getAsString();
                                    System.out.println("추출된 summary: " + summary);
                                    reviewSummary.put(ReviewKeywordList.get(i), summary);
                                } else {
                                    System.err.println("Summary key not found in JSON response");
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            return ResponseEntity.notFound().build();
                        }
                    }
                }
            } else {
                System.out.println("저장된 리뷰 키워드가 없습니다.");
            }

            System.out.println("reviewSummary값 입니다. : " + reviewSummary);
            System.out.println("새로운 리뷰 요약을 저장합니다.");
            reviewMapper.saveReviewSummary(shoe_seq, reviewSummary.toString());
        }
        response.put("reviewSummary", reviewSummary);
        return ResponseEntity.ok().body(response);
    }
}
