package com.project.prueba.inditex.infrastructure.rest;


import com.project.prueba.inditex.domain.model.Price;
import com.project.prueba.inditex.domain.port.PriceServicePort;
import com.project.prueba.inditex.infrastructure.adapter.controller.dto.PriceResponse;
import com.project.prueba.inditex.infrastructure.adapter.controller.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceServicePort priceService;
    private final PriceMapper priceMapper;

    @GetMapping
    public ResponseEntity<Page<PriceResponse>> getPrices(
            @RequestParam LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Integer brandId,
            Pageable pageable) {

        Page<Price> prices = priceService.findApplicablePrices(applicationDate, productId, brandId, pageable);

        return ResponseEntity.ok(prices.map(priceMapper::priceToPriceResponse));
    }
}
