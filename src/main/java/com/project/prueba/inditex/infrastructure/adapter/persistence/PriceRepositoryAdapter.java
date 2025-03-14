package com.project.prueba.inditex.infrastructure.adapter.persistence;

import com.project.prueba.inditex.domain.port.PriceRepositoryPort;

import com.project.prueba.inditex.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public Page<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Integer brandId, Pageable pageable) {
        return priceJpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                applicationDate, applicationDate, productId, brandId, pageable);
    }
}
