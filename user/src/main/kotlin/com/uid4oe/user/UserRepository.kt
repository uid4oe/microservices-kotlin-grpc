package com.uid4oe.user

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserModel,String>