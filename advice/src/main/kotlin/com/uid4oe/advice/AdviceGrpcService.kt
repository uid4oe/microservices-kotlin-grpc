package com.uid4oe.advice


import advicepb.Advice
import advicepb.AdviceServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class AdviceGrpcService(val business: AdviceBusiness) : AdviceServiceGrpc.AdviceServiceImplBase() {

    override fun getAdvice(
        request: Advice.GetUserAdviceRequest,
        responseObserver: StreamObserver<Advice.GetUserAdviceResponse>
    ) {
        val adviceModel = business.getAdvice(request.userId).get()
        val response =
            Advice.GetUserAdviceResponse.newBuilder()
                .setAdvice(adviceModel.advice)
                .setCreatedAt(AdviceModel.localDateToTimestamp(adviceModel.created_at))
                .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun createUpdateAdvice(
        request: Advice.CreateUpdateAdviceRequest,
        responseObserver: StreamObserver<Advice.CreateUpdateAdviceResponse>
    ) {
        business.createUpdateAdvice(
            AdviceModel(
                user_id = request.userId,
                advice = request.advice
            )
        )

        responseObserver.onNext(Advice.CreateUpdateAdviceResponse.newBuilder().build())
        responseObserver.onCompleted()
    }

}
