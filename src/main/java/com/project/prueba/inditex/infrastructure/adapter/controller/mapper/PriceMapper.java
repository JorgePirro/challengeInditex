package com.project.prueba.inditex.infrastructure.adapter.controller.mapper;

import com.project.prueba.inditex.domain.model.Price;
import com.project.prueba.inditex.infrastructure.adapter.controller.dto.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "brand.name", target = "brandName")
    PriceResponse priceToPriceResponse(Price price);
}
