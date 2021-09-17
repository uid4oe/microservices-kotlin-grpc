package com.uid4oe.user

import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.bson.types.ObjectId
import userpb.UserServiceGrpc
import userpb.User

@GrpcService
class UserGrpcService(val business: UserBusiness) : UserServiceGrpc.UserServiceImplBase() {

    override fun getUserDetails(
        request: User.GetUserDetailsRequest,
        responseObserver: StreamObserver<User.GetUserDetailsResponse>
    ) {
        val details = business.getUserDetails(request.id)
        val response = UserModel.modelToGetUserDetailsReponse(details)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun getUsers(request: User.GetUsersRequest, responseObserver: StreamObserver<User.GetUsersResponse>) {
        val users = business.getUsers()
        val response = User.GetUsersResponse.newBuilder().addAllUsers(users.map {
            UserModel.modelToGetUsersResponse(it)
        }).build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun createUpdateUser(
        request: User.CreateUpdateUserRequest,
        responseObserver: StreamObserver<User.CreateUpdateUserResponse>
    ) {
        var uid = request.id

        if (request.operation.equals(User.Operation.CREATE)) {
            uid = ObjectId().toHexString()
        }

        business.createUpdateUser(
            UserModel(
                id = uid,
                name = request.name,
                greeting = request.greeting,
                age = request.age,
                salary = request.salary,
                power = request.power
            )
        )
        responseObserver.onNext(User.CreateUpdateUserResponse.newBuilder().setId(uid).build())
        responseObserver.onCompleted()
    }

}