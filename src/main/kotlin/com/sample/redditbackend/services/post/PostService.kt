package com.sample.redditbackend.services.post

interface PostService {

    fun addPost()

    fun getAllCommentsInPost()

    fun addLikeToPost()

    fun removeLikeFromPost()
}