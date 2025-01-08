package com.config.server.configserver.service;

import com.config.server.configserver.dto.ReleaseDto;

import java.util.List;

public interface ReleaseService {
    public List<ReleaseDto> getAllReleaseByConfig(String configkey, String configQuery);
}
