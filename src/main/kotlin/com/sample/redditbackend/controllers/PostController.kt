package com.sample.redditbackend.controllers

import com.sample.redditbackend.dtos.posts.PostRequest
import com.sample.redditbackend.dtos.posts.PostResponse
import com.sample.redditbackend.dtos.users.UserRequest
import com.sample.redditbackend.services.posts.PostService
import com.sample.redditbackend.utils.Endpoints
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController(Endpoints.Post.post)
class PostController {

    @Autowired
    lateinit var postService: PostService

    @PostMapping(Endpoints.Post.savePost)
    fun savePost(@Valid @RequestBody postRequest: PostRequest): ResponseEntity<PostResponse> {
        try {
            return ResponseEntity.ok(postService.savePost(postRequest))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Post.getPost)
    fun getPost(@PathVariable id: String): ResponseEntity<PostResponse> {
        try {
            return ResponseEntity.ok(postService.getPost(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Post.getPostByName)
    fun getPostByName(@PathVariable name: String): ResponseEntity<List<PostResponse>> {
        try {
            return ResponseEntity.ok(postService.getPostByHeading(name))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Post.getPostBySubreddit)
    fun getPostBySubreddit(@PathVariable name: String): ResponseEntity<List<PostResponse>> {
        try {
            return ResponseEntity.ok(postService.getPostBySubredditName(name))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Post.getPostByUser)
    fun getPostByUser(@PathVariable id: String): ResponseEntity<List<PostResponse>> {
        try {
            return ResponseEntity.ok(postService.getPostByUserId(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PutMapping(Endpoints.Post.upvotePost)
    fun upvotePost(@RequestBody postRequest: PostRequest): ResponseEntity<PostResponse> {
        try {
            return ResponseEntity.ok(postService.upvotePost(postRequest))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PutMapping(Endpoints.Post.downvotePost)
    fun downvotePost(@RequestBody postRequest: PostRequest): ResponseEntity<PostResponse> {
        try {
            return ResponseEntity.ok(postService.downvotePost(postRequest))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}