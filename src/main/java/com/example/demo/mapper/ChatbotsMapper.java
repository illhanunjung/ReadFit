package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.Chatbots;

@Mapper
public interface ChatbotsMapper {
   void saveChatbots(List<Chatbots> chatbotsList);
   List<Chatbots> findAllChatsGroupedBySessionSeq(String mem_id);
    // Chatbots findChatByMemIdAndSession_seq(@Param("mem_id") String mem_id, @Param("session_seq") String session_seq);
}
