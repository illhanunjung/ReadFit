package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Chatbots;

@Mapper
public interface ChatbotsMapper {
   void saveChatbots(List<Chatbots> chatbotsList);
   List<Chatbots> findAllChatsGroupedBySessionSeq(String mem_id);
   @Select("SELECT chat_seq, mem_id, contents, creat_at, bot_answer, session_seq FROM CHATBOTS WHERE session_seq = #{session_seq} AND mem_id = #{mem_id}")
   List<Chatbots> findChatByMemIdAndSession_seq(@Param("mem_id") String mem_id, @Param("session_seq") String session_seq);
}
