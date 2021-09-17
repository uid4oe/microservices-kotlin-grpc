package com.uid4oe.advice

import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class AdviceGrpcExceptionHandler{

    @GrpcExceptionHandler(Exception::class)
    fun handleException(e: Exception) =
        Status.INTERNAL.withDescription(e.message).withCause(e)
}