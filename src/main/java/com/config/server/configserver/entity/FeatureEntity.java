package com.config.server.configserver.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="feature")
public class FeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String desc;
    @ManyToMany(mappedBy = "features")
    private List<AppEntity> apps;
    @ManyToMany(mappedBy = "features", cascade = CascadeType.PERSIST)
    private List<ConfigEntity> configs;

    public List<ConfigEntity> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigEntity> configs) {
        this.configs = configs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
