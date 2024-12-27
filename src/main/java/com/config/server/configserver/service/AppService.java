package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.FeatureEntity;

import java.util.List;

public interface AppService {
    public List<AppDto> getAllApps();
    public List<FeatureDto> getAllFeaturesByApp(int appId);
    public List<ConfigDto> getAllConfigsByDto(int appId);
}
