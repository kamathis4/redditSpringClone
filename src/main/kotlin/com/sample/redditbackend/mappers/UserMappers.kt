package com.sample.redditbackend.mappers

import com.sample.redditbackend.dtos.users.UserRequest
import com.sample.redditbackend.dtos.users.UserResponse
import com.sample.redditbackend.entities.UserEntity

fun UserRequest.toUserEntity(): UserEntity {
    return UserEntity(
        userName = this.userName,
        userEmail = this.userEmail,
        userPassword = this.userPassword,
        userDesc = this.userDesc
    )
}

fun UserEntity.toUserResponse(): UserResponse {
    return UserResponse(
        userId = this.id,
        userName = this.userName,
        userEmail = this.userEmail,
        userDesc = this.userDesc
    )
}