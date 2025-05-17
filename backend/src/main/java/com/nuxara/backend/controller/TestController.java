package com.nuxara.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TestController {

    private final RestTemplate restTemplate;
    private final String supabaseUrl;
    private final String supabaseKey;

    @Autowired
    public TestController(
            RestTemplate restTemplate,
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.key}") String supabaseKey) {
        this.restTemplate = restTemplate;
        this.supabaseUrl = supabaseUrl;
        this.supabaseKey = supabaseKey;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", supabaseKey);
        
        return ResponseEntity.ok("Server is running with Supabase integration! URL: " + supabaseUrl);
    }
} 