package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.repo.AppRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppRepo appRepo;

    public List<AppDto> getAllApps() {
        List<AppEntity> appEntityList = appRepo.findAll();
       List<AppDto> appDtoList= appEntityList.stream().map(appEntity ->
        {
            AppDto appDto= new AppDto();
            BeanUtils.copyProperties(appEntity, appDto);
            return appDto;
        }).collect(Collectors.toList());
       return appDtoList;
    }

    @Override
    public List<FeatureDto> getAllFeaturesByApp(int appId) {
       List<FeatureEntity> featureEntityList= appRepo.getAllFeaturesByApp(appId);
       List<FeatureDto> featureDtoList=featureEntityList.stream().map(featureEntity -> {
           FeatureDto featureDto= new FeatureDto();
           BeanUtils.copyProperties(featureEntity, featureDto);
           return featureDto;
       }).collect(Collectors.toList());
       return featureDtoList;
    }

    @Override
    public List<ConfigDto> getAllConfigsByDto(int appId) {
        List<ConfigEntity> configEntityList=appRepo.getAllConfigsByApp(appId);
        List<ConfigDto> configDtoList=configEntityList.stream().map(configEntity -> {
            ConfigDto configDto= new ConfigDto();
            BeanUtils.copyProperties(configEntity,configDto);
            return configDto;
        }).collect(Collectors.toList());
        return configDtoList;
    }


}
