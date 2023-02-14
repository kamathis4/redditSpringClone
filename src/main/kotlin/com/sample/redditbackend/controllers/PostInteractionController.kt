package com.sample.redditbackend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("")
class PostInteractionController {

    @PostMapping
    fun addCommentToPost(){

    }

    @GetMapping
    fun getAllCommentsInPost(){

    }

    @PostMapping
    fun addLikeToPost(){

    }

    @PostMapping
    fun removeLikeFromPost(){

    }
}