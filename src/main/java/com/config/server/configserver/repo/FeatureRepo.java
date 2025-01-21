package com.config.server.configserver.repo;

import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FeatureRepo extends JpaRepository<FeatureEntity, Integer> {

    public Page<FeatureEntity> findAllByAppId(int appId, Pageable pageable);

    @Query(value = "select a.name from app a join feature f on a.id=f.app_id where f.name=:featureName", nativeQuery = true)
    public List<String> getAllAppsByFeature(String featureName);

}
