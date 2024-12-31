package com.journalapp.journal.APP.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("HealthCheck")
    public String Healthcheck(){
        return "ok!";
    }
}
