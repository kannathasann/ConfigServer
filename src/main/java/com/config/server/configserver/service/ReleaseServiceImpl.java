package com.config.server.configserver.service;

import com.config.server.configserver.dto.ReleaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ReleaseDto> getAllReleaseByConfig(String configkey, String name) {

        String sql1 = "SELECT config_value FROM config WHERE config_key = ?";
        String configValue = jdbcTemplate.queryForObject(sql1, new Object[]{configkey}, String.class);
        List<String> enabledValues = (configValue != null && !configValue.isEmpty())
                ? Arrays.asList(configValue.split(","))
                : new ArrayList<>();

        String sql = "select * from " + name + "";
        List<ReleaseDto> releaseStrategyDtoList = jdbcTemplate.query(sql, (resultSet, rows) ->
        {
            ReleaseDto releaseStrategyDto = new ReleaseDto();
            releaseStrategyDto.setId(resultSet.getInt("id"));
            releaseStrategyDto.setName(resultSet.getString("name"));
            String tempId = String.valueOf(releaseStrategyDto.getId());
            if (enabledValues.contains(tempId))
                releaseStrategyDto.setStatus("enabled");

            else releaseStrategyDto.setStatus("disabled");

            return releaseStrategyDto;
        });

        return releaseStrategyDtoList;

    }
}
