package com.uid4oe.user

import org.springframework.stereotype.Service

@Service
class UserBusiness(val repository: UserRepository) {

    fun getUsers(): List<UserModel> = repository.findAll()

    fun getUserDetails(id: String) = repository.findById(id).get()

    fun createUpdateUser(user: UserModel) = repository.save(user)

}

