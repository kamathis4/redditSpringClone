package com.sample.redditbackend.controllers

import com.sample.redditbackend.dtos.users.UserRequest
import com.sample.redditbackend.dtos.users.UserResponse
import com.sample.redditbackend.mappers.toUserEntity
import com.sample.redditbackend.services.user.UserService
import com.sample.redditbackend.utils.Endpoints
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController(Endpoints.User.user)
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping(Endpoints.User.saveUser)
    fun saveUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        try {
            return ResponseEntity.ok(userService.addUser(userRequest))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.User.getUser)
    fun getUser(@PathVariable id: String): ResponseEntity<UserResponse> {
        try {
            return ResponseEntity.ok(userService.getUser(id))
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.User.getByName)
    fun getUserName(@PathVariable name: String): ResponseEntity<UserResponse> {
        try {
            return ResponseEntity.ok(userService.getUserByName(name))
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}