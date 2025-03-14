package com.project.prueba.inditex.infrastructure.adapter.persistence;

import com.project.prueba.inditex.domain.model.Price;
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

class PriceRepositoryAdapterTest {
    @Mock
    private PriceJpaRepository priceJpaRepository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

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

        when(priceJpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId, pageable)).thenReturn(prices);

        Page<Price> result = priceRepositoryAdapter.findApplicablePrices(applicationDate, productId, brandId, pageable);

        assertEquals(prices, result);
    }

    @Test
    void findApplicablePrices_returnsEmpty_whenNoPricesExist() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Integer brandId = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Price> emptyPrices = Page.empty();

        when(priceJpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId, pageable)).thenReturn(emptyPrices);

        Page<Price> result = priceRepositoryAdapter.findApplicablePrices(applicationDate, productId, brandId, pageable);

        assertEquals(emptyPrices, result);
    }

}