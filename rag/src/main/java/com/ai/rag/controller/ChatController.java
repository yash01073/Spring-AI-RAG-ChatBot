package com.ai.rag.controller;

import com.ai.rag.service.ChatBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @Autowired
    ChatBot chatBot;

    @GetMapping(value = "/chat")
    public String getJoke(@RequestParam String prompt){
        return chatBot.chat(prompt);
    }
}
