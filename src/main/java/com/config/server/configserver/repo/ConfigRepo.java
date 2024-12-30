package com.config.server.configserver.repo;

import com.config.server.configserver.entity.AppEntity;
import com.config.server.configserver.entity.ConfigEntity;
import com.config.server.configserver.entity.FeatureEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConfigRepo extends JpaRepository<ConfigEntity, Integer> {

    @Query("select c from ConfigEntity c join c.features f where f.id=:featureId")
    public List<ConfigEntity> getAllConfigsByFeature(@Param("featureId") int featureId);

    @Query("SELECT f FROM FeatureEntity f JOIN f.configs c WHERE c.id = :configId")
    public List<FeatureEntity> getAllFeaturesByConfig(@Param("configId") int configId);

    @Query("select a from AppEntity a join a.configs c where c.id=:configId")
    public List<AppEntity> getAllAppsByConfig(@Param("configId") int configId);

    public ConfigEntity findByConfigKey(String configKey);
    @Modifying
    @Transactional
    @Query("update ConfigEntity c set c.configValue=:configValue where c.configKey=:configKey ")
    public int updateConfig(String configKey, String configValue);


    @Modifying
    @Transactional
    @Query(value = "insert into feature_config(config_id,feature_id) values(:configId, :featureId)", nativeQuery = true)
    public int createFeatureConfig(int configId, int featureId);

    @Modifying
    @Transactional
    @Query("delete from ConfigEntity c where c.configKey=:configKey")
    public int deleteConfig(String configKey);
}
