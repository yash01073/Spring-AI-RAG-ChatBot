package com.ai.rag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBot {

    @Autowired
    ReTrain reTrain;

    @Autowired
    PromptService promptService;

    public String chat(String message){
        reTrain.retrainAI();
        return promptService.getPromptResponse(message);
    }
}
