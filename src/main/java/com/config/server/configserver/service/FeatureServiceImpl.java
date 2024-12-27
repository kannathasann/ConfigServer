package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.repo.FeatureRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureRepo
            featureRepo;

    @Override
    public List<FeatureDto> getAllFeatures() {

        List<FeatureEntity> featureEntityList = featureRepo.findAll();
        List<FeatureDto> featureDtoList = featureEntityList.stream().map(featureEntity -> {
            FeatureDto featureDto = new FeatureDto();
            BeanUtils.copyProperties(featureEntity, featureDto);
            return featureDto;
        }).collect(Collectors.toList());
        return featureDtoList;

    }

    @Override
    public List<AppDto> getAllAppsByFeature(int featureId) {
        List<AppEntity> appEntityList = featureRepo.getAllAppsByFeature(featureId);
        List<AppDto> appDtoList = appEntityList.stream().map(appEntity -> {
            AppDto appDto = new AppDto();
            BeanUtils.copyProperties(appEntity, appDto);
            return appDto;
        }).collect(Collectors.toList());
        return appDtoList;
    }

    @Override
    public List<ConfigDto> getAllConfigsByFeature(int featureId) {
        List<ConfigEntity> configEntityList = featureRepo.getAllConfigsByFeature(featureId);
        List<ConfigDto> configDtoList = configEntityList.stream().map(configEntity -> {
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(configEntity, configDto);
            return configDto;
        }).collect(Collectors.toList());
        return configDtoList;
    }

    @Override
    @Transactional
    public FeatureDto createFeature(int appId, FeatureDto featureDto) {
        FeatureEntity featureEntity= new FeatureEntity();
        BeanUtils.copyProperties(featureDto, featureEntity);
        FeatureEntity savedEntity=featureRepo.save(featureEntity);
        int featureId= savedEntity.getId();

       featureRepo.createAppFeature(appId, featureId);
       FeatureDto responseDto= new FeatureDto();
       BeanUtils.copyProperties(savedEntity, responseDto);
       return responseDto;

    }

    @Override
    @Transactional
    public String deleteFeature(int id) {
        featureRepo.deleteById(id);
        return "deleted successfully..";

    }
}