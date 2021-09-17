package com.uid4oe.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import userpb.User as UserPB

@Document("user_kt")
data class UserModel(
    @Id
    val id: String? = null,
    var name: String? = "",
    var age: Int = 0,
    var greeting: String? = "",
    var salary: Int = 0,
    var power: String? = "",
) {
    companion object {
        fun modelToGetUsersResponse(u: UserModel) = UserPB.GetUserResponse.newBuilder()
            .setId(u.id)
            .setName(u.name)
            .setAge(u.age)
            .setGreeting(u.greeting)
            .build()

        fun modelToGetUserDetailsReponse(user: UserModel) = UserPB.GetUserDetailsResponse.newBuilder()
            .setSalary(user.salary)
            .setPower(user.power)
            .build()
    }
}