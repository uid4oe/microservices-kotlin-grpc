package com.uid4oe.bff

import advicepb.AdviceServiceGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import userpb.UserServiceGrpc
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import userpb.User as UserPB
import advicepb.Advice as AdvicePB


@Service
class BffBusiness {

    @GrpcClient("user")
    private lateinit var userClient: UserServiceGrpc.UserServiceBlockingStub

    @GrpcClient("advice")
    private lateinit var adviceClient: AdviceServiceGrpc.AdviceServiceBlockingStub

    fun getUsers(): List<UserSummary> {
        val response = userClient.getUsers(UserPB.GetUsersRequest.newBuilder().build())
        return response.usersList.map {
            UserSummary(
                id = it.id,
                name = it.name,
                age = it.age,
                greeting = it.greeting
            )
        }

    }

    fun getUserDetails(id: String): UserDetails {
        val response = userClient.getUserDetails(
            UserPB.GetUserDetailsRequest.newBuilder()
                .setId(id).build()
        )
        val adviceResponse = adviceClient.getAdvice(AdvicePB.GetUserAdviceRequest.newBuilder().setUserId(id).build())
        return UserDetails(
            salary = response.salary,
            power = response.power,
            advice = adviceResponse.advice,
            created_at = Instant
                .ofEpochSecond(adviceResponse.createdAt.seconds, adviceResponse.createdAt.nanos.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        )
    }

    fun createUser(user: User): String {
        val createdUserId = userClient.createUpdateUser(User.toCreateUpdateRequest(user,userpb.User.Operation.CREATE))
        adviceClient.createUpdateAdvice(
            AdvicePB.CreateUpdateAdviceRequest.newBuilder()
                .setUserId(createdUserId.id)
                .setAdvice(user.advice).build()
        )
        return user.id!!
    }

    fun updateUser(id: String, user: User): String {
        user.id = id
        userClient.createUpdateUser(User.toCreateUpdateRequest(user,userpb.User.Operation.UPDATE))
        return user.id!!
    }

    fun updateAdvice(advice : Advice) : String{
        adviceClient.createUpdateAdvice(
            AdvicePB.CreateUpdateAdviceRequest.newBuilder()
                .setUserId(advice.user_id)
                .setAdvice(advice.advice).build()
        )
        return "OK"
    }

}

