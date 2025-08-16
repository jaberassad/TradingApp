package com.assadosman.Trading.App.controllers;
import com.assadosman.Trading.App.model.Assets.AssetEntity;
import com.assadosman.Trading.App.model.user.User;
import com.assadosman.Trading.App.testDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
public class AssetControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AssetControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void createAsset() throws Exception {
        AssetEntity asset = testDataUtil.createEntity();
        String userJson = objectMapper.writeValueAsString(asset);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
