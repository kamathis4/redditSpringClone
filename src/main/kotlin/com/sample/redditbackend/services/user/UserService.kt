package com.sample.redditbackend.services.user

import com.sample.redditbackend.dtos.users.UserRequest
import com.sample.redditbackend.dtos.users.UserResponse

interface UserService {

    fun addUser(userRequest: UserRequest):UserResponse

    fun getUser(userId:String):UserResponse

    fun getUserByName(userName:String):UserResponse
}