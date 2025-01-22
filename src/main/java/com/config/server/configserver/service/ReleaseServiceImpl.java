package com.config.server.configserver.service;

import com.config.server.configserver.dto.ReleaseDto;
import com.config.server.configserver.exception.SqlQueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<ReleaseDto> getAllReleaseByConfig(String configkey, String configQuery) {

        String sql = "SELECT config_values FROM config WHERE config_key = ?";
        String configValue = jdbcTemplate.queryForObject(sql, new Object[]{configkey}, String.class);
        List<String> enabledValues = (configValue != null && !configValue.isEmpty())
                ? Arrays.asList(configValue.split(","))
                : new ArrayList<>();


          List<ReleaseDto> releaseStrategyDtoList = jdbcTemplate.query(configQuery, (resultSet, rows) ->
            {
                ReleaseDto releaseStrategyDto = new ReleaseDto();
                releaseStrategyDto.setId(resultSet.getInt(1));
                releaseStrategyDto.setName(resultSet.getString(2));
                String tempId = String.valueOf(releaseStrategyDto.getId());
                if (enabledValues.contains(tempId))
                    releaseStrategyDto.setStatus("enabled");

                else releaseStrategyDto.setStatus("disabled");

                return releaseStrategyDto;
            });


        return releaseStrategyDtoList;

    }
}
