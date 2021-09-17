package com.uid4oe.bff

import java.time.LocalDateTime
import userpb.User as UserPB

class User(
    var id: String? = "",
    val name: String,
    val age: Int,
    val salary: Int,
    val greeting: String,
    val power: String,
    val advice: String,
) {
    companion object {
        fun toCreateUpdateRequest(user: User,operation : UserPB.Operation) = UserPB.CreateUpdateUserRequest.newBuilder()
            .setOperation(operation)
            .setId(user.id)
            .setName(user.name)
            .setAge(user.age)
            .setSalary(user.salary)
            .setGreeting(user.greeting)
            .setPower(user.power)
            .build()
    }
}

class UserSummary(
    val id: String,
    val name: String,
    val age: Int,
    val greeting: String
)

class UserDetails(
    val salary: Int,
    val power: String,
    val advice: String,
    val created_at: LocalDateTime,
)

class Advice(
    val user_id: String,
    val advice: String,
    val created_at: LocalDateTime? = null,
)

class BffResponse<T>(
    val data: T,
    val error: String? = ""
)