package com.uid4oe.bff

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BffExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) =
        ResponseEntity(BffResponse(null, error = e.message), HttpStatus.INTERNAL_SERVER_ERROR)
}

