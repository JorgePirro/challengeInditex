package com.project.prueba.inditex.application.service;

import com.project.prueba.inditex.domain.model.Price;
import com.project.prueba.inditex.domain.port.PriceRepositoryPort;
import com.project.prueba.inditex.domain.port.PriceServicePort;
import com.project.prueba.inditex.infrastructure.exceptions.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceServicePort {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Page<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Integer brandId, Pageable pageable) {
        return Optional.of(priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId, pageable))
                .filter(page -> !page.isEmpty())
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No se encontró un precio para el producto %d y marca %d en la fecha %s",
                                productId, brandId, applicationDate)
                ));
    }
}
