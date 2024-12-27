package com.config.server.configserver.controller;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/getAllConfigs")
    public ResponseEntity<List<ConfigDto>> getAllConfigs() {
        List<ConfigDto> configDtoList = configService.getAllConfigs();
        return new ResponseEntity<>(configDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllFeaturesByConfig/{id}")
    public ResponseEntity<List<FeatureDto>> getAllFeaturesByConfig(@PathVariable("id") int configId) {
        List<FeatureDto> featureDtoList = configService.getAllFeaturesByConfig(configId);
        return new ResponseEntity<>(featureDtoList, HttpStatus.OK);

    }

    @GetMapping("/getAllAppsByConfig/{id}")
    public ResponseEntity<List<AppDto>> getAllAppsByConfig(@PathVariable("id") int configId) {
        List<AppDto> appDtoList = configService.getAllAppsByConfig(configId);
        return new ResponseEntity<>(appDtoList, HttpStatus.OK);

    }

    @PostMapping("/createConfig")
    public ResponseEntity<ConfigDto> createConfig(@RequestParam int featureId, @RequestBody ConfigDto configDto) {
        ConfigDto responseDto = configService.createConfig(featureId, configDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateConfig")
    public ResponseEntity<ConfigDto> updateConfig(@RequestParam String configKey, @RequestBody List<String> configValuesList) {
        ConfigDto configDto = configService.updateConfig(configKey, configValuesList);
        return new ResponseEntity<>(configDto, HttpStatus.OK);

    }

    @DeleteMapping("/deleteConfig")
    public ResponseEntity<String> deleteConfig(@RequestParam String configKey) {

        String response = configService.deleteConfig(configKey);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
