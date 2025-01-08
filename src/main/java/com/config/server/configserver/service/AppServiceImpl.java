package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.repo.AppRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppRepo appRepo;

    public List<AppDto> getAllApps() {
        List<AppEntity> appEntityList = appRepo.findAll();
        List<AppDto> appDtoList = appEntityList.stream().map(appEntity ->
        {
            AppDto appDto = new AppDto();
            BeanUtils.copyProperties(appEntity, appDto);
            return appDto;
        }).collect(Collectors.toList());
        return appDtoList;
    }

    @Override
    public AppDto createApp(AppDto appDto) {
        AppEntity appEntity = new AppEntity();
        BeanUtils.copyProperties(appDto, appEntity);
        AppEntity savedEntity = appRepo.save(appEntity);
        AppDto response = new AppDto();
        BeanUtils.copyProperties(appEntity, response);
        return response;

    }


}
