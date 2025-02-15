package com.config.server.configserver.service;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import com.config.server.configserver.repo.FeatureRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureRepo featureRepo;

    @Override
    public List<FeatureDto> getAllFeatures() {

        List<FeatureEntity> featureEntityList = featureRepo.findAll();
        List<FeatureDto> featureDtoList = featureEntityList.stream().map(featureEntity -> {
            FeatureDto featureDto = new FeatureDto();
            BeanUtils.copyProperties(featureEntity, featureDto);
            return featureDto;
        }).collect(Collectors.toList());
        return featureDtoList;

    }

    @Override
    public Page<FeatureDto> getAllFeaturesByApp(int id, int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<FeatureEntity> featureEntityList = featureRepo.findAllByAppId(id, pageable);
        Page<FeatureDto> featureDtoList = featureEntityList.map(featureEntity -> {
            FeatureDto featureDto = new FeatureDto();
            BeanUtils.copyProperties(featureEntity, featureDto);
            return featureDto;
        });
        return featureDtoList;
    }


    @Override
    public FeatureDto createFeature(FeatureDto featureDto) {
        FeatureEntity featureEntity = new FeatureEntity();
        BeanUtils.copyProperties(featureDto, featureEntity);
        FeatureEntity savedEntity = featureRepo.save(featureEntity);
        FeatureDto responseDto = new FeatureDto();
        BeanUtils.copyProperties(savedEntity, responseDto);
        return responseDto;

    }

    @Override
    @Transactional
    public String deleteFeature(int id) {
        featureRepo.deleteById(id);
        return "deleted successfully..";

    }

    @Override
    public List<String> getAllAppsByFeature(String featureName) {
        List<String> appList=featureRepo.getAllAppsByFeature(featureName);
        return appList;
    }
}
