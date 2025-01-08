package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FeatureService {
    public List<FeatureDto> getAllFeatures();

    public List<FeatureDto> getAllFeaturesByApp(int id);


    public FeatureDto createFeature( FeatureDto featureDto);

    public String deleteFeature(int id);

}
