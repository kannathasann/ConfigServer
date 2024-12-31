package com.config.server.configserver.controller;


import com.config.server.configserver.dto.AppDto;
import com.config.server.configserver.dto.ConfigDto;
import com.config.server.configserver.dto.FeatureDto;
import com.config.server.configserver.service.AppService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AppControllerTest {

    @InjectMocks
    AppController appController;

    @Mock
    AppService appService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllApps()
    {

        AppDto appDto1 = new AppDto();
        appDto1.setId(1);
        appDto1.setName("test1");
        appDto1.setDesc("desc1");
        AppDto appDto2= new AppDto();
        appDto2.setId(2);
        appDto2.setName("test2");
        appDto2.setDesc("desc2");
        List<AppDto> mockResponse= Arrays.asList(appDto1, appDto2);
        when(appService.getAllApps()).thenReturn(mockResponse);

        ResponseEntity<List<AppDto>> response=appController.getAllApps();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());


    }


    @Test
    public void testGetAllFeaturesByApp()
    {
        FeatureDto featureDto1= new FeatureDto();
        featureDto1.setId(1);
        featureDto1.setName("test1");
        featureDto1.setDesc("desc1");
        List<FeatureDto> mockResponse=Arrays.asList(featureDto1);
        when(appService.getAllFeaturesByApp(1)).thenReturn(mockResponse);

        ResponseEntity<List<FeatureDto>> response=appController.getAllFeaturesByApp(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }


    @Test
    public void testGetAllConfigsByApp()
    {
        ConfigDto configDto= new ConfigDto();
        configDto.setId(1);
        configDto.setConfigName("test1");
        configDto.setConfigKey("key1");
        configDto.setConfigValue("value1");
        configDto.setDesc("des");
        List<ConfigDto> mockResponse= Arrays.asList(configDto);


        when(appService.getAllConfigsByDto(1)).thenReturn(mockResponse);

        ResponseEntity<List<ConfigDto>> response=appController.getAllConfigsByApp(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }
}
