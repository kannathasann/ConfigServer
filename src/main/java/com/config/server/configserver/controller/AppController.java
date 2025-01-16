package com.config.server.configserver.controller;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("/getAllApps")
    public ResponseEntity<Page<AppDto>> getAllApps(@RequestParam int page, @RequestParam int size)
    {
        Page<AppDto> appDtoList= appService.getAllApps(page, size);
        return new ResponseEntity<>(appDtoList, HttpStatus.OK);
    }

    @PostMapping("/createApp")
    public ResponseEntity<AppDto> createApp(@RequestBody AppDto appDto)
    {
        AppDto response=appService.createApp(appDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteApp/{id}")
    public ResponseEntity<String> deleteApp(@PathVariable int id)
    {
          String response= appService.deleteApp(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
