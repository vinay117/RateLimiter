package com.design.ratelimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    RateLimiter rateLimiter;
    @GetMapping("/")
    public ResponseEntity<?> greet() {
        if(rateLimiter.canServe()) {
            return ResponseEntity.ok("Hello your request is processed");
        } else {
            return new ResponseEntity<>("Too many requests please try again later", HttpStatusCode.valueOf(429));
        }
    }
}
