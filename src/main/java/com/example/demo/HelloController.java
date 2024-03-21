package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
        return "안녕못한다 현재 서버 시간은 " + new Date() + "\n";
    }

    @GetMapping("/api/board")
    public String HelloWorld() {
        return "과연 될까? \n";
    }

}