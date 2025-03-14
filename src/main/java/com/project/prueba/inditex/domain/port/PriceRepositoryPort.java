package com.project.prueba.inditex.domain.port;

import com.project.prueba.inditex.domain.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {

    Page<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Integer brandId, Pageable pageable);
}
