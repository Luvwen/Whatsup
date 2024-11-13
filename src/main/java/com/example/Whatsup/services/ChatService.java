package com.example.Whatsup.services;

import com.example.Whatsup.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public Boolean createNewChat(Integer userOne, Integer userTwo){
        try {
            Boolean result = chatRepository.createNewChat(userOne, userTwo);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
