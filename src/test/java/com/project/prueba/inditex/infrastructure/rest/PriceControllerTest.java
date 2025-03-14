package com.project.prueba.inditex.infrastructure.rest;

import com.project.prueba.inditex.domain.model.Brand;
import com.project.prueba.inditex.domain.model.Price;
import com.project.prueba.inditex.domain.port.PriceServicePort;
import com.project.prueba.inditex.infrastructure.adapter.controller.dto.PriceResponse;
import com.project.prueba.inditex.infrastructure.adapter.controller.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceServicePort priceService;

    @MockBean
    private PriceMapper priceMapper;

    @Test
    public void testGetPrices() throws Exception {
        // Parámetros de entrada
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Integer brandId = 1;

        // Creamos un Brand de prueba
        Brand testBrand = Brand.builder()
                .id(brandId)
                .name("ZARA")
                .build();

        // Creamos un Price de prueba con la relación a Brand
        Price testPrice = Price.builder()
                .id(1L)
                .brand(testBrand)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .productId(productId)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        // Creamos un PriceResponse de prueba a partir del Price
        PriceResponse testPriceResponse = PriceResponse.builder()
                .id(testPrice.getId())
                .brandName(testBrand.getName())
                .startDate(testPrice.getStartDate())
                .endDate(testPrice.getEndDate())
                .priceList(testPrice.getPriceList())
                .productId(testPrice.getProductId())
                .priority(testPrice.getPriority())
                .price(testPrice.getPrice())
                .currency(testPrice.getCurrency())
                .build();

        // Simulamos que el servicio retorna una página con el Price de prueba
        PageImpl<Price> pricePage = new PageImpl<>(Collections.singletonList(testPrice));
        when(priceService.findApplicablePrices(
                ArgumentMatchers.eq(applicationDate),
                ArgumentMatchers.eq(productId),
                ArgumentMatchers.eq(brandId),
                ArgumentMatchers.any(Pageable.class))
        ).thenReturn(pricePage);

        // Simulamos que el mapper convierte el Price en PriceResponse
        when(priceMapper.priceToPriceResponse(ArgumentMatchers.any(Price.class)))
                .thenReturn(testPriceResponse);

        // Ejecutamos la petición GET al endpoint y validamos la respuesta
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].brandName").value("ZARA"))
                .andExpect(jsonPath("$.content[0].productId").value(productId))
                .andExpect(jsonPath("$.content[0].price").value(35.50))
                .andExpect(jsonPath("$.content[0].currency").value("EUR"));
    }

}