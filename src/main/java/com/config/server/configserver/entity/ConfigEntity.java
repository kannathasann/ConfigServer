package com.config.server.configserver.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="config")
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="description")
    private String desc;
    @Column(name="config_name")
    private String configName;
    @Column(name="config_key")
    private String configKey;
    @Column(name="config_value")
    private String configValue;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Feature_Config",
            joinColumns = @JoinColumn(name = "config_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private List<FeatureEntity> features;

    @ManyToMany(mappedBy = "configs",cascade = CascadeType.PERSIST)
    private List<AppEntity> apps;

    public List<FeatureEntity> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureEntity> features) {
        this.features = features;
    }

    public List<AppEntity> getApps() {
        return apps;
    }

    public void setApps(List<AppEntity> apps) {
        this.apps = apps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
