package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository:JpaRepository<UserEntity, String> {

    fun findByUserNameContainingIgnoreCase(userName:String): Optional<UserEntity>
}