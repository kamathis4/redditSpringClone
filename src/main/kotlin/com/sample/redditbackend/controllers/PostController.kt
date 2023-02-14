package com.sample.redditbackend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("")
class PostController {

    @PostMapping
    fun addPost(){

    }

    @GetMapping
    fun getPost(){

    }

    @GetMapping
    fun getAllPostsInSubreddit(){

    }
}