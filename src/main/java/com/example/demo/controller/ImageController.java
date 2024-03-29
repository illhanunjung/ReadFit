package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mapper.MemberMapper;

@RestController
@RequestMapping("/api/img")
@CrossOrigin(origins = "*")
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
            return ResponseEntity.ok().body("File uploaded successfully: " + filename);
        } catch (IOException e) {
            // 업로드 실패 시 예외 처리
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Could not upload the file: " + file.getOriginalFilename());
        }

    }


}


