package com.config.server.configserver.repo;

import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeatureRepo extends JpaRepository<FeatureEntity, Integer> {

    @Query("SELECT a FROM AppEntity a JOIN a.features f WHERE f.id = :featureId")
    public List<AppEntity> getAllAppsByFeature(@Param("featureId") int featureId);



    @Modifying
    @Transactional
    @Query(value = "insert into app_feature(app_id, feature_id) values(:appId, :featureId)", nativeQuery = true)
    public int createAppFeature(int appId, int featureId);
}
