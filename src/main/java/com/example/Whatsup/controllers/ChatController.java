package com.example.Whatsup.controllers;

import com.example.Whatsup.services.ChatService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Data
@RequestMapping("/chat")
public class ChatController {
    public ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    /* @ExceptionHandler(ChatCreationException.class)
    public ResponseEntity<Map<String, Object>> handleChatCreationException(ChatCreationException ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", 400);
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    } */

    @PostMapping("/{userOne}/{userTwo}")
    public ResponseEntity<?> createNewChat(@PathVariable Integer userOne, @PathVariable Integer userTwo){
        Boolean createChatResponse = chatService.createNewChat(userOne, userTwo);

        if(!createChatResponse){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("")
                    .buildAndExpand(1)
                    .toUri();

            return ResponseEntity.created(location).build();
        }

        return ResponseEntity.badRequest().body("Error al crear el chat");
    }
}
