package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeatureService {
    public List<FeatureDto> getAllFeatures();
    public List<AppDto> getAllAppsByFeature(int featureId);

    public FeatureDto createFeature(int appId, FeatureDto featureDto );
    public String deleteFeature(int id);

}
