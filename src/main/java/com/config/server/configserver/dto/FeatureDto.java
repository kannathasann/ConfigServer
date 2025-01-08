package com.config.server.configserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeatureDto {

    private int id;
    private String name;
    private String desc;
    private String featureKey;
    private int appId;

}
