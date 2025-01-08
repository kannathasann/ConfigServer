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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigRepo configRepo;

    @Autowired
    FeatureRepo featureRepo;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

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
//    @Cacheable(value = "featureConfigs", key="#featureId.toString()")
    public List<ConfigDto> getAllConfigsByFeature(int featureId) {
        System.out.println("Fetching Config data from external DB........");
        List<ConfigEntity> configEntityList = configRepo.findAllByFeatureId(featureId);
        List<ConfigDto> configDtoList = configEntityList.stream().map(configEntity -> {
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(configEntity, configDto);
            return configDto;
        }).collect(Collectors.toList());
        return configDtoList;
    }





    @Override
    @Transactional
//    @CachePut(value = "featureConfigs", key = "#configDto.featureId.toString()")
    public List<ConfigDto> createConfig( ConfigDto configDto) {
        // Convert DTO to Entity
        ConfigEntity configEntity = new ConfigEntity();
        BeanUtils.copyProperties(configDto, configEntity);
        // Save the new ConfigEntity
        ConfigEntity savedEntity = configRepo.save(configEntity);
        int featureId=configDto.getFeatureId();
        List<ConfigEntity> configEntityList=configRepo.findAllByFeatureId(featureId);
        List<ConfigDto> configDtoList= new ArrayList<>();
        for(ConfigEntity configEntity1: configEntityList)
        {
            ConfigDto configDto1=  new ConfigDto();
            BeanUtils.copyProperties(configEntity1, configDto1);
            configDtoList.add(configDto1);

        }
        return configDtoList;
    }


    @Override
//    @CachePut(value = "featureConfigs", key = "#featureId.toString()")
    public List<ConfigDto> updateConfig(int featureId,String configKey, List<String> configValuesList) {
        String configValue = String.join(",", configValuesList);
        int result = configRepo.updateConfig(configKey, configValue);

            List<ConfigEntity> configEntityList=configRepo.findAllByFeatureId(featureId);
            List<ConfigDto> configDtoList= new ArrayList<>();
            for(ConfigEntity configEntity1: configEntityList)
            {
                ConfigDto configDto1=  new ConfigDto();
                BeanUtils.copyProperties(configEntity1, configDto1);
                configDtoList.add(configDto1);

            }
            return configDtoList;

    }

    @Override
    public String deleteConfig(String configKey) {
        int i = configRepo.deleteConfig(configKey);
        if (i > 0)
            return "" + configKey + " is deleted successfullyy.." + "";
        else return "" + configKey + " is failed to delete.." + "";
    }
}
