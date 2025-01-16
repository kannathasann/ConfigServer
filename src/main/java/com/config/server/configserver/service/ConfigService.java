package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.ConfigEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConfigService {
    public List<ConfigDto> getAllConfigs();
    public Page<ConfigDto> getAllConfigsByFeature(int featureId, int page, int size);

    public ConfigDto createConfig(ConfigDto configDto);

    public String updateConfig(int featureId,String configKey, List<String> configValuesList);

    public String deleteConfig(int id);
}

