package com.config.server.configserver.controller;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FeatureController {
    @Autowired
    FeatureService featureService;

    @GetMapping("/getAllFeatures")
    public ResponseEntity<List<FeatureDto>> getAllFeatures() {
        List<FeatureDto> featureDtoList = featureService.getAllFeatures();
        return new ResponseEntity<>(featureDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllFeaturesByApp/{id}")
    public ResponseEntity<List<FeatureDto>> getAllFeaturesByApp(@PathVariable int id) {
        List<FeatureDto> featureDtoList = featureService.getAllFeaturesByApp(id);
        return new ResponseEntity<>(featureDtoList, HttpStatus.OK);
    }

    @PostMapping("/createFeature")
    public ResponseEntity<FeatureDto> createFeature(@RequestBody FeatureDto featureDto) {
        FeatureDto response = featureService.createFeature(featureDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteFeature")
    public ResponseEntity<String> deleteFeature(@RequestParam int id) {
        String result = featureService.deleteFeature(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
