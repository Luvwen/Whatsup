package com.example.Whatsup.services;

import com.example.Whatsup.entities.Message;
import com.example.Whatsup.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }


    public List<Message> getUserMessages(Integer userId){
        List<Message> userMessages = messageRepository.getUserMessages(userId);

        return userMessages;
    }

    public Boolean insertUserMessage(Message message, Integer userId){
        Boolean result = messageRepository.insertUserMessage(message, userId);

        return result;
    }
}
