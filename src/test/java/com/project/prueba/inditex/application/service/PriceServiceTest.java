package com.project.prueba.inditex.application.service;

import com.project.prueba.inditex.domain.model.Price;
import com.project.prueba.inditex.domain.port.PriceRepositoryPort;
import com.project.prueba.inditex.infrastructure.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findApplicablePrices_returnsPrices_whenPricesExist() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Price> prices = new PageImpl<>(Collections.singletonList(new Price()));

        when(priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId, pageable)).thenReturn(prices);

        Page<Price> result = priceService.findApplicablePrices(applicationDate, productId, brandId, pageable);

        assertEquals(prices, result);
    }

    @Test
    void findApplicablePrices_throwsException_whenNoPricesExist() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Price> emptyPrices = Page.empty();

        when(priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId, pageable)).thenReturn(emptyPrices);

        assertThrows(PriceNotFoundException.class, () ->
                priceService.findApplicablePrices(applicationDate, productId, brandId, pageable)
        );
    }

}