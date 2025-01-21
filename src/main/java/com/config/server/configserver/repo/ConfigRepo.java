package com.config.server.configserver.repo;

import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConfigRepo extends JpaRepository<ConfigEntity, Integer> {


    public Page<ConfigEntity> findAllByFeatureId(int featureId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update ConfigEntity c set c.configValues=:configValue where c.configKey=:configKey ")
    public int updateConfig(String configKey, String configValue);


}
