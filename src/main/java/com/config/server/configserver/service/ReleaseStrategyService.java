package com.config.server.configserver.service;

import com.config.server.configserver.dto.ReleaseStrategyDto;

import java.util.List;

public interface ReleaseStrategyService {
    public List<ReleaseStrategyDto> getAllReleaseStrategyByConfig(String configkey, String name);
}
