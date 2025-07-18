package com.example.ReferalProject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health")
    public String health(){
        return "Chill bro I am fine.. ";
    }
}
