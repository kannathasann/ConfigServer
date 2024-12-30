package com.config.server.configserver.service;

import com.config.server.configserver.dto.ReleaseStrategyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReleaseStrategyServiceImpl implements ReleaseStrategyService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ReleaseStrategyDto> getAllReleaseStrategyByConfig(String configkey, String name) {

        String sql1 = "SELECT config_value FROM config WHERE config_key = ?";
        String configValue = jdbcTemplate.queryForObject(sql1, new Object[]{configkey}, String.class);
        List<String> enabledValues = (configValue != null && !configValue.isEmpty())
                ? Arrays.asList(configValue.split(","))
                : new ArrayList<>();

        String sql = "select * from " + name + "";
        List<ReleaseStrategyDto> releaseStrategyDtoList = jdbcTemplate.query(sql, (resultSet, rows) ->
        {
            ReleaseStrategyDto releaseStrategyDto = new ReleaseStrategyDto();
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
