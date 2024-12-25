package com.config.server.configserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }
}
