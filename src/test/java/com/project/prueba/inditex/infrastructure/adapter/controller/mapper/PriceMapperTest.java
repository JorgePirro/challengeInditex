package com.project.prueba.inditex.infrastructure.adapter.controller.mapper;

import com.project.prueba.inditex.domain.model.Brand;
import com.project.prueba.inditex.domain.model.Price;
import com.project.prueba.inditex.infrastructure.adapter.controller.dto.PriceResponse;
import com.project.prueba.inditex.infrastructure.adapter.controller.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceMapperTest {

    private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

    @Test
    void priceToPriceResponse_mapsFieldsCorrectly() {
        Price price = new Price();
        price.setBrand(Brand.builder()
                        .name("BrandName")
                        .build());
        PriceResponse priceResponse = priceMapper.priceToPriceResponse(price);
        assertEquals("BrandName", priceResponse.getBrandName());
    }

    @Test
    void priceToPriceResponse_handlesNullBrand() {
        Price price = new Price();
        price.setBrand(null);
        PriceResponse priceResponse = priceMapper.priceToPriceResponse(price);
        assertEquals(null, priceResponse.getBrandName());
    }

}