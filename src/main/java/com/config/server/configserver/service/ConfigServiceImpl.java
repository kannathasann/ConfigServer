package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.repo.ConfigRepo;
import com.config.server.configserver.repo.FeatureRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigRepo configRepo;

    @Autowired
    FeatureRepo featureRepo;

    @Override
    public List<ConfigDto> getAllConfigs() {
        List<ConfigEntity> configEntityList = configRepo.findAll();
        List<ConfigDto> configDtoList = configEntityList.stream().map(configEntity -> {
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(configEntity, configDto);
            return configDto;
        }).collect(Collectors.toList());

        return configDtoList;
    }

    @Override
    public List<FeatureDto> getAllFeaturesByConfig(int configId) {
        List<FeatureEntity> featureEntityList = configRepo.getAllFeaturesByConfig(configId);
        List<FeatureDto> featureDtoList = featureEntityList.stream().map(featureEntity -> {
            FeatureDto featureDto = new FeatureDto();
            BeanUtils.copyProperties(featureEntity, featureDto);
            return featureDto;
        }).collect(Collectors.toList());
        return featureDtoList;
    }

    @Override
    public List<AppDto> getAllAppsByConfig(int configId) {
        List<AppEntity> appEntityList = configRepo.getAllAppsByConfig(configId);
        List<AppDto> appDtoList = appEntityList.stream().map(appEntity -> {
            AppDto appDto = new AppDto();
            BeanUtils.copyProperties(appEntity, appDto);
            return appDto;
        }).collect(Collectors.toList());
        return appDtoList;

    }


    @Override
    @Transactional
    public ConfigDto createConfig(int featureId, ConfigDto configDto) {
        // Convert DTO to Entity
        ConfigEntity configEntity = new ConfigEntity();
        BeanUtils.copyProperties(configDto, configEntity);
        // Save the new ConfigEntity
        ConfigEntity savedEntity = configRepo.save(configEntity);
        int configId = savedEntity.getId();

        int result = configRepo.createFeatureConfig(configId, featureId);


        // Convert the saved ConfigEntity back to DTO
        ConfigDto savedConfigDto = new ConfigDto();
        BeanUtils.copyProperties(savedEntity, savedConfigDto);

        return savedConfigDto;
    }


    @Override
    public ConfigDto updateConfig(String configKey, List<String> configValuesList) {
        String configValue = String.join(",", configValuesList);
        int result = configRepo.updateConfig(configKey, configValue);
        if (result > 0) {
            ConfigEntity savedEntity = configRepo.findByConfigKey(configKey);
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(savedEntity, configDto);
            return configDto;
        }
        return null;

    }

    @Override
    public String deleteConfig(String configKey) {
        int i = configRepo.deleteConfig(configKey);
        if (i > 0)
            return "" + configKey + " is deleted successfullyy.." + "";
        else return "" + configKey + " is failed to delete.." + "";
    }
}
