package com.project.prueba.inditex.infrastructure.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("uri=/test");
    }

    @Test
    void handlePriceNotFoundException_returnsNotFoundStatus() {
        PriceNotFoundException exception = new PriceNotFoundException("Price not found");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlePriceNotFoundException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Price not found", response.getBody().getMessage());
        assertEquals("uri=/test", response.getBody().getDetails());
    }

    @Test
    void handleGlobalException_returnsInternalServerErrorStatus() {
        Exception exception = new Exception("Internal server error");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGlobalException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody().getMessage());
        assertEquals("uri=/test", response.getBody().getDetails());
    }

}