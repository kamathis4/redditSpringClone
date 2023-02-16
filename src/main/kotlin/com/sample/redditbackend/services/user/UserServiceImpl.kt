package com.sample.redditbackend.services.user

import com.sample.redditbackend.dtos.users.UserRequest
import com.sample.redditbackend.dtos.users.UserResponse
import com.sample.redditbackend.mappers.toUserEntity
import com.sample.redditbackend.mappers.toUserResponse
import com.sample.redditbackend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun addUser(userRequest: UserRequest): UserResponse {
        try {
            val user = userRequest.toUserEntity()
            val savedUser = userRepository.save(user)
            return savedUser.toUserResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getUser(userId: String): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow { Exception("Unable to get user") }
            return user.toUserResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getUserByName(userName: String): UserResponse {
        try {
            val user = userRepository.findByUserNameContainingIgnoreCase(userName)
                .orElseThrow { Exception("Unable to get user") }
            return user.toUserResponse()
        } catch (e: Exception) {
            throw e
        }
    }
}