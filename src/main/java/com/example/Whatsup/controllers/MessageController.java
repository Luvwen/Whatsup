package com.example.Whatsup.controllers;

import com.example.Whatsup.entities.Message;
import com.example.Whatsup.services.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/{userId}")
public class MessageController {
    public MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getUserMessages(@PathVariable Integer userId){
        List<Message> userMessages = messageService.getUserMessages(userId);
        if (!userMessages.isEmpty()){
            return ResponseEntity.ok().body(userMessages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mensajes no encontrados");
        }
    }

    @PostMapping("/message")
    public ResponseEntity<?> insertUserMessage(@RequestBody Message message, @PathVariable Integer userId){
        Boolean insertResult = messageService.insertUserMessage(message, userId);
        if(!insertResult){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("")
                    .buildAndExpand(userId)
                    .toUri();

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().body("Error en la inserción, formato no válido.");
        }
    }
}
