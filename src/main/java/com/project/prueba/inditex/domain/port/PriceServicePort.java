package com.project.prueba.inditex.domain.port;

import com.project.prueba.inditex.domain.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PriceServicePort {

    /**
     * Devuelve los precios aplicables a un producto en una fecha específica para una marca determinada.
     *
     * @param applicationDate fecha de aplicación del precio
     * @param productId       identificador del producto
     * @param brandId         identificador de la cadena (marca)
     * @param pageable        configuración para paginación
     * @return página de precios aplicables ordenados por prioridad
     */
    Page<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Integer brandId, Pageable pageable);
}
