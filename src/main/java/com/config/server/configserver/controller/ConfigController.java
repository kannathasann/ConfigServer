package com.config.server.configserver.controller;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test method", HttpStatus.OK);
    }

    @GetMapping("/getAllConfigs")
    public ResponseEntity<List<ConfigDto>> getAllConfigs() {
        List<ConfigDto> configDtoList = configService.getAllConfigs();
        return new ResponseEntity<>(configDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllConfigsByFeature/{id}")
    public ResponseEntity<Page<ConfigDto>> getAllConfigsByFeature(@PathVariable("id") int featureId, @RequestParam int page, @RequestParam int size) {
        Page<ConfigDto> configDtoList = configService.getAllConfigsByFeature(featureId, page, size);
        return new ResponseEntity<>(configDtoList, HttpStatus.OK);

    }


    @PostMapping("/createConfig")
    public ResponseEntity<ConfigDto> createConfig( @RequestBody ConfigDto configDto) {
        ConfigDto responseDto = configService.createConfig( configDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateConfig")
    public ResponseEntity<String> updateConfig(@RequestParam int featureId, @RequestParam String configKey, @RequestBody List<String> configValuesList) {
        String response = configService.updateConfig(featureId, configKey, configValuesList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/deleteConfig/{id}")
    public ResponseEntity<String> deleteConfig(@PathVariable int id) {

        String response = configService.deleteConfig(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
