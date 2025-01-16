package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.FeatureEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AppService {
    public Page<AppDto> getAllApps(int page, int size);
    public AppDto createApp(AppDto appDto);
    public String deleteApp(int id);
}
