package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.repo.AppRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {
    private static  final Logger logger=LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    AppRepo appRepo;

    public Page<AppDto> getAllApps(int page, int size) {
        long startTime=System.currentTimeMillis();
        logger.info("get all apps method entered...");
        Pageable pageable= PageRequest.of(page, size);
        Page<AppEntity> appEntityList = appRepo.findAll(pageable);
        Page<AppDto> appDtoList = appEntityList.map(appEntity ->
        {
            AppDto appDto = new AppDto();
            BeanUtils.copyProperties(appEntity, appDto);
            return appDto;
        });
        logger.info("total time taken " +(System.currentTimeMillis()-startTime)/1000.0+ " seconds");
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

    @Override
    public String deleteApp(int id) {
        appRepo.deleteById(id);
         return "deleted successfulllyyy...";
    }


}
