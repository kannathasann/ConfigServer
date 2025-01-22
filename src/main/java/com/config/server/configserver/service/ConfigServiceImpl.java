package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.dto.ReleaseDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.exception.ResourceNotFoundException;
import com.config.server.configserver.exception.SqlQueryException;
import com.config.server.configserver.repo.ConfigRepo;
import com.config.server.configserver.repo.FeatureRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
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
    JdbcTemplate jdbcTemplate;

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

        try
        {
            String configQuery= configDto.getConfigQuery();
            List<ReleaseDto> releaseStrategyDtoList = jdbcTemplate.query(configQuery, (resultSet, rows) ->
            {
                ReleaseDto releaseStrategyDto = new ReleaseDto();
                releaseStrategyDto.setId(resultSet.getInt(1));
                releaseStrategyDto.setName(resultSet.getString(2));
                return releaseStrategyDto;
            });

        }
        catch(DataIntegrityViolationException e)
        {throw new SqlQueryException("please give valid query...first select id then name");

        }
        catch(Exception e)
        {
            throw new SqlQueryException("Please give valid query...recheck your table and column name");

        }
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
