package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.ConfigEntity;

import java.util.List;

public interface ConfigService {
    public List<ConfigDto> getAllConfigs();

    public List<FeatureDto> getAllFeaturesByConfig(int configId);

    public List<AppDto> getAllAppsByConfig(int configId);

    public ConfigDto createConfig(int featureId,ConfigDto configDto);

    public ConfigDto updateConfig(String configKey, List<String> configValuesList);

    public String deleteConfig(String configKey);
}

