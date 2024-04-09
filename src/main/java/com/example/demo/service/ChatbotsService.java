package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ChatbotsMapper;
import com.example.demo.model.Chatbots;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ChatbotsService {
    
    private final ChatbotsMapper chatbotsMapper;

    @Autowired
    public ChatbotsService(ChatbotsMapper chatbotsMapper) {
        this.chatbotsMapper = chatbotsMapper;
    }

 
    @Transactional
    public void saveChatbots(List<Chatbots> chatbotsList) {
        chatbotsMapper.saveChatbots(chatbotsList);
    }

    

    public List<Chatbots> findAllChatsGroupedBySessionSeq(String mem_id) {
        return chatbotsMapper.findAllChatsGroupedBySessionSeq(mem_id);
    }

    // public Chatbots findChatByMemIdAndSession_seq(String mem_id, String session_seq) {
    //     return chatbotsMapper.findChatByMemIdAndSession_seq(mem_id, session_seq);
    // }
}
