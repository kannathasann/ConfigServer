package com.config.server.configserver.controller;

import com.config.server.configserver.dto.ReleaseStrategyDto;
import com.config.server.configserver.service.ReleaseStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReleaseStrategyController {

    @Autowired
    ReleaseStrategyService releaseStrategyService;

    @GetMapping("/getAllReleaseStrategyByConfig")
    public ResponseEntity<List<ReleaseStrategyDto>> getAllReleaseStrategyByConfig(@RequestParam String configkey, @RequestParam String name)
    {
        List<ReleaseStrategyDto> releaseStrategyDtoList=releaseStrategyService.getAllReleaseStrategyByConfig(configkey, name);
        return new ResponseEntity<>(releaseStrategyDtoList, HttpStatus.OK);

    }
}
