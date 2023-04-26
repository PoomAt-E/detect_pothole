package com.detect_pothole.detect_pothole.global.error

import com.detect_pothole.detect_pothole.global.error.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler
    fun handleRuntimeException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val errorCode: ErrorCode = e.errorCode
        val response = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message
        )
        return ResponseEntity.status(errorCode.status).body(response)
    }

    @ExceptionHandler
    protected fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {
        val response: ErrorResponse = ErrorResponse.Companion.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult())
        return ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST)
    }
}