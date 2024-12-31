package com.management.Student.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    @RequestMapping("/health")
    public String healthCheck() {
        return "Application is running";
    }
    
}
