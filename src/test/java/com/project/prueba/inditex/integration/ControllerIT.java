package com.project.prueba.inditex.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "/test-data.sql")
    void getPrices_returnsPrices_whenPricesExist() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].brandName").value("ZARA"))
                .andExpect(jsonPath("$.content[0].productId").value(35455))
                .andExpect(jsonPath("$.content[0].price").value(35.50))
                .andExpect(jsonPath("$.content[0].currency").value("EUR"));
    }


    @Test
    @Sql(scripts = "/test-data.sql")
    void getPrices_returnsPrices_whenMultiplePricesExist() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].brandName").value("ZARA"))
                .andExpect(jsonPath("$.content[0].productId").value(35455))
                .andExpect(jsonPath("$.content[0].price").value(35.50))
                .andExpect(jsonPath("$.content[0].currency").value("EUR"));
    }
}
