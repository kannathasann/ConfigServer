package com.config.server.configserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String desc;
    @Column(name = "config_key")
    private String configKey;
    @Column(name = "config_values")
    private String configValues;
    @Column(name = "config_query")
    private String configQuery;
    @Column(name = "feature_id")
    private int featureId;


}
