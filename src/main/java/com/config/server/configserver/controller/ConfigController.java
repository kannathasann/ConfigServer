package com.config.server.configserver.controller;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${predefined.config.names}")
    private String predefinedConfigNames;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/getAllConfigs")
    public ResponseEntity<List<ConfigDto>> getAllConfigs() {
        List<ConfigDto> configDtoList = configService.getAllConfigs();
        return new ResponseEntity<>(configDtoList, HttpStatus.OK);
    }
    @GetMapping("/getAllConfigsByFeature/{id}")
    public ResponseEntity<List<ConfigDto>> getAllConfigsByFeature(@PathVariable("id") int featureId)
    {
        List<ConfigDto> configDtoList=configService.getAllConfigsByFeature(featureId);
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
    public ResponseEntity<List<ConfigDto>> createConfig(@RequestParam int featureId, @RequestBody ConfigDto configDto) {
        List<ConfigDto> responseDto = configService.createConfig(featureId, configDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateConfig")
    public ResponseEntity<List<ConfigDto>> updateConfig(@RequestParam int featureId, @RequestParam String configKey, @RequestBody List<String> configValuesList) {
        List<ConfigDto> configDto = configService.updateConfig(featureId,configKey, configValuesList);
        return new ResponseEntity<>(configDto, HttpStatus.OK);

    }

    @DeleteMapping("/deleteConfig")
    public ResponseEntity<String> deleteConfig(@RequestParam String configKey) {

        String response = configService.deleteConfig(configKey);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/config/predefinedNames")
    public List<String> getPredefinedConfigNames() {
        return Arrays.asList(predefinedConfigNames.split(","));
    }
}
