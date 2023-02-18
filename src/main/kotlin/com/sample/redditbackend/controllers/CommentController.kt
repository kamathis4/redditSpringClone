package com.sample.redditbackend.controllers

import com.sample.redditbackend.dtos.comments.CommentRequest
import com.sample.redditbackend.dtos.comments.CommentResponse
import com.sample.redditbackend.dtos.posts.PostRequest
import com.sample.redditbackend.dtos.posts.PostResponse
import com.sample.redditbackend.services.comments.CommentService
import com.sample.redditbackend.utils.Endpoints
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController(Endpoints.Comment.comment)
class CommentController {

    @Autowired
    lateinit var commentService: CommentService

    @PostMapping(Endpoints.Comment.saveComment)
    fun saveComment(@RequestBody commentRequest: CommentRequest): ResponseEntity<CommentResponse> {
        try {
            return ResponseEntity.ok(commentService.saveComment(commentRequest))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Comment.getComment)
    fun getComment(@PathVariable id: String): ResponseEntity<CommentResponse> {
        try {
            return ResponseEntity.ok(commentService.getComment(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Comment.getCommentsByPost)
    fun getCommentByPost(@PathVariable id: String): ResponseEntity<List<CommentResponse>> {
        try {
            return ResponseEntity.ok(commentService.getAllCommentsByPost(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Comment.getCommentsByUser)
    fun getCommentByUser(@PathVariable id: String): ResponseEntity<List<CommentResponse>> {
        try {
            return ResponseEntity.ok(commentService.getAllCommentByUser(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PatchMapping(Endpoints.Comment.upvoteComment)
    fun upvoteComment(@PathVariable id: String): ResponseEntity<CommentResponse> {
        try {
            return ResponseEntity.ok(commentService.upvoteComment(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PatchMapping(Endpoints.Comment.downvoteComment)
    fun downvoteComment(@PathVariable id: String): ResponseEntity<CommentResponse> {
        try {
            return ResponseEntity.ok(commentService.downvoteComment(id))
        } catch (e: Exception) {
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}