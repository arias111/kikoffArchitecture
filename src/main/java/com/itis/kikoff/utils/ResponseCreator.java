package com.itis.kikoff.utils;

import org.springframework.http.ResponseEntity;

public abstract class ResponseCreator {

    protected <T> ResponseEntity<T> createGoodResponse(T body) {
        return ResponseEntity.ok(body);
    }

    protected ResponseEntity createErrorResponse(ErrorEntity errorEntity) {
        return ResponseEntity.status(errorEntity.getStatus()).body(errorEntity);
    }
}