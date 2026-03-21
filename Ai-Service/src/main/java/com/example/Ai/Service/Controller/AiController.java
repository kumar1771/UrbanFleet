package com.example.Ai.Service.Controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Ai")
public class AiController {
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @GetMapping("/openAi")
    public String OpenAiChatMessage(@RequestParam("query") String query){
        return openAiChatModel.call(query);
    }
}
