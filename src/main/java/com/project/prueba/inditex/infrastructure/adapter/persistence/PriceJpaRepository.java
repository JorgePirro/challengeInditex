package com.project.prueba.inditex.infrastructure.adapter.persistence;

import com.project.prueba.inditex.domain.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PriceJpaRepository extends JpaRepository<Price, Long> {

    Page<Price> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long productId,
            Integer brandId,
            Pageable pageable
    );
}
