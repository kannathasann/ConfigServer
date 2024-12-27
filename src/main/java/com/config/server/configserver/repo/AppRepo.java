package com.config.server.configserver.repo;


import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRepo extends JpaRepository<AppEntity, Integer> {

    @Query("SELECT f FROM FeatureEntity f JOIN f.apps a WHERE a.id = :appId")
    public List<FeatureEntity> getAllFeaturesByApp(@Param("appId") int appId);

    @Query("select c from ConfigEntity c join c.apps a where a.id=:appId")
    public List<ConfigEntity> getAllConfigsByApp(@Param("appId") int appId);


}
