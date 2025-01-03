package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.ConfigEntity;

import java.util.List;

public interface ConfigService {
    public List<ConfigDto> getAllConfigs();
    public List<ConfigDto> getAllConfigsByFeature(int featureId);

    public List<FeatureDto> getAllFeaturesByConfig(int configId);

    public List<AppDto> getAllAppsByConfig(int configId);

    public List<ConfigDto> createConfig(int featureId,ConfigDto configDto);

    public List<ConfigDto> updateConfig(int featureId,String configKey, List<String> configValuesList);

    public String deleteConfig(String configKey);
}

