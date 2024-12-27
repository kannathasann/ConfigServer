package com.config.server.configserver.controller;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("/getAllApps")
    public ResponseEntity<List<AppDto>> getAllApps()
    {
        List<AppDto> appDtoList= appService.getAllApps();
        return new ResponseEntity<>(appDtoList, HttpStatus.OK);
    }
    @GetMapping("/getAllFeaturesByApp/{id}")
    public ResponseEntity<List<FeatureDto>> getAllFeaturesByApp(@PathVariable("id") int appId) {
        List<FeatureDto> featureDtoList=appService.getAllFeaturesByApp(appId);
        return new ResponseEntity<>(featureDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllConfigsByApp/{id}")
    public ResponseEntity<List<ConfigDto>> getAllConfigsByApp(@PathVariable("id") int appId) {
        List<ConfigDto> ConfigDtoList=appService.getAllConfigsByDto(appId);
        return new ResponseEntity<>(ConfigDtoList, HttpStatus.OK);
    }
}
