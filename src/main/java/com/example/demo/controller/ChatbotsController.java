package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Chatbots;
import com.example.demo.service.ChatbotsService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatbotsController {

    @Autowired
    private ChatbotsService chatbotService;


  
    @PostMapping("/chats")
    public ResponseEntity<?> saveChatbots(@RequestBody List<Chatbots> chatbotsList) {
        chatbotService.saveChatbots(chatbotsList);
        return ResponseEntity.ok().build();
    }

    

    @GetMapping("/chatdata/{mem_id}")    
    public ResponseEntity<List<Chatbots>> findAllChatsGroupedBySessionSeq(@PathVariable String mem_id) {
        return ResponseEntity.ok(chatbotService.findAllChatsGroupedBySessionSeq(mem_id));
    }

    // @GetMapping("/{mem_id}/{session_seq}")
    // public ResponseEntity<Chatbots> getChatByMemIdAndSession_seq(@PathVariable String mem_id, @PathVariable String session_seq) {
    //     return ResponseEntity.ok(chatbotService.findChatByMemIdAndSession_seq(mem_id, session_seq));
    // }
}
