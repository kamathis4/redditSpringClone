package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.UserEntity
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    /** Repository functions
     * Save user
     * Get User
     */

    @Autowired
    lateinit var userRepository: UserRepository

    val userEntity = UserEntity(
        userName = "name",
        userDesc = "desc",
        userEmail = "email",
        userPassword = "password"
    )

    @Test
    fun saveUser() {
        val savedUser = userRepository.save(userEntity)
        Assertions.assertEquals(savedUser, userEntity)
    }

    @Test
    fun getUser(){
        val savedUser = userRepository.save(userEntity)
        val gotUser = userRepository.findById(savedUser.id).get()

        Assertions.assertEquals(savedUser, gotUser)
        Assertions.assertEquals(userEntity, gotUser)
    }
}