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
public class FeatureController
{
    @Autowired
    FeatureService featureService;

    @GetMapping("/getAllFeatures")
    public ResponseEntity<List<FeatureDto>> getAllFeatures()
    {
        List<FeatureDto> featureDtoList=featureService.getAllFeatures();
        return new ResponseEntity<>(featureDtoList, HttpStatus.OK);


    }
    @GetMapping("/getAllAppsByFeature/{id}")
    public ResponseEntity<List<AppDto>> getAllAppsByFeature(@PathVariable("id") int featureId)
    {
        List<AppDto> appDtoList=featureService.getAllAppsByFeature(featureId);
        return new ResponseEntity<>(appDtoList, HttpStatus.OK);

    }

    @GetMapping("/getAllConfigsByFeature/{id}")
    public ResponseEntity<List<ConfigDto>> getAllConfigsByFeature(@PathVariable("id") int featureId)
    {
        List<ConfigDto> configDtoList=featureService.getAllConfigsByFeature(featureId);
        return new ResponseEntity<>(configDtoList, HttpStatus.OK);

    }

    @PostMapping("/createFeature")
    public ResponseEntity<FeatureDto> createFeature(@RequestParam int appId, @RequestBody FeatureDto featureDto)
    {
        FeatureDto response=featureService.createFeature(appId, featureDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteFeature")
    public ResponseEntity<String> deleteFeature(@RequestParam int id)
    {
        String result=featureService.deleteFeature(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
