package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.exception.ResourceNotFoundException;
import com.config.server.configserver.repo.ConfigRepo;
import com.config.server.configserver.repo.FeatureRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    ConfigRepo configRepo;


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
    public Page<ConfigDto> getAllConfigsByFeature(int featureId, int page, int size) {
        logger.info("Fetching Config data from external DB........");
        Pageable pageable = PageRequest.of(page, size);
        Page<ConfigEntity> configEntityList = configRepo.findAllByFeatureId(featureId, pageable);
        Page<ConfigDto> configDtoList = configEntityList.map(configEntity -> {
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(configEntity, configDto);
            return configDto;
        });
        return configDtoList;
    }


    @Override
    @Transactional
//    @CachePut(value = "featureConfigs", key = "#configDto.featureId.toString()")
    public ConfigDto createConfig(ConfigDto configDto) {
        // Convert DTO to Entity
        ConfigEntity configEntity = new ConfigEntity();
        BeanUtils.copyProperties(configDto, configEntity);
        // Save the new ConfigEntity
        ConfigEntity savedEntity = configRepo.save(configEntity);
        ConfigDto response = new ConfigDto();
        BeanUtils.copyProperties(savedEntity, response);
        return response;
    }


    @Override
//    @CachePut(value = "featureConfigs", key = "#featureId.toString()")
    public String updateConfig(int featureId, String configKey, List<String> configValuesList) {
        String configValue = String.join(",", configValuesList);
        int result = configRepo.updateConfig(configKey, configValue);
        return "updated ....";


    }

    @Override
    public String deleteConfig(int id) {
        configRepo.deleteById(id);

        return " deleted successfullyy..";

    }
}
