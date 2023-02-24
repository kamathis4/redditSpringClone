package com.sample.redditbackend.services.posts

import com.sample.redditbackend.dtos.posts.PostRequest
import com.sample.redditbackend.dtos.posts.PostResponse

interface PostService {

    fun savePost(postRequest: PostRequest): PostResponse

    fun getPost(postId: String): PostResponse

    fun getPostByHeading(postHeading: String): List<PostResponse>

    fun getPostBySubredditName(subredditName: String): List<PostResponse>

    fun getPostByUserId(userId: String): List<PostResponse>

    fun upvotePost(postId: String): PostResponse

    fun downvotePost(postId: String): PostResponse
}