package com.config.server.configserver.controller;

import com.config.server.configserver.dto.ReleaseDto;
import com.config.server.configserver.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReleaseController {

    @Autowired
    ReleaseService releaseService;

    @GetMapping("/getAllReleaseByConfig")
    public ResponseEntity<List<ReleaseDto>> getAllReleaseByConfig(@RequestParam String configkey, @RequestParam String name)
    {
        List<ReleaseDto> releaseStrategyDtoList= releaseService.getAllReleaseByConfig(configkey, name);
        return new ResponseEntity<>(releaseStrategyDtoList, HttpStatus.OK);

    }
}
