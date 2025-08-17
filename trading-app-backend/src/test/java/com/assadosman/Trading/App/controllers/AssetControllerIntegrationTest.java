package com.assadosman.Trading.App.controllers;
import com.assadosman.Trading.App.model.Assets.AssetEntity;
import com.assadosman.Trading.App.model.Assets.AssetsService;
import com.assadosman.Trading.App.model.user.User;
import com.assadosman.Trading.App.testDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AssetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AssetsService assetsService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDatabase() {
        jdbcTemplate.execute("TRUNCATE TABLE assets");
    }

    @Test
    public void createAsset() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/assets/AAPL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        assertTrue(assetsService.findByID("AAPL").isPresent());
    }

    @Test
    public void updateAsset() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/assets/AAPL")
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/assets/AAPL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound());

        assertTrue(assetsService.findByID("AAPL").isPresent());
    }

}
