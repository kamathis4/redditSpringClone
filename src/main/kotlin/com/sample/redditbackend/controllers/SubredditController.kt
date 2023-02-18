package com.sample.redditbackend.controllers

import com.sample.redditbackend.dtos.subreddits.SubredditRequest
import com.sample.redditbackend.dtos.subreddits.SubredditResponse
import com.sample.redditbackend.dtos.users.UserRequest
import com.sample.redditbackend.mappers.toUserEntity
import com.sample.redditbackend.services.subreddit.SubredditService
import com.sample.redditbackend.utils.Endpoints
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController(Endpoints.Subreddit.subreddit)
class SubredditController {

    @Autowired
    lateinit var subredditService: SubredditService

    @PostMapping(Endpoints.Subreddit.saveSubreddit)
    fun saveSubreddit(@RequestBody subredditRequest: SubredditRequest): ResponseEntity<SubredditResponse> {
        try {
            return ResponseEntity.ok(subredditService.addSubreddit(subredditRequest))
        }catch (e:Exception){
            print(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Subreddit.getSubreddit)
    fun getSubreddit(@PathVariable id: String): ResponseEntity<SubredditResponse>{
        try {
            return ResponseEntity.ok(subredditService.getSubreddit(id))
        }catch (e:Exception){
            print(e.message)
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Subreddit.getSubredditByName)
    fun getSubredditByName(@PathVariable name: String): ResponseEntity<List<SubredditResponse>>{
        try {
            return ResponseEntity.ok(subredditService.getSubredditByName(name))
        }catch (e:Exception){
            print(e.message)
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping(Endpoints.Subreddit.getSubredditByUser)
    fun getSubredditByUser(@PathVariable("name") userRequest: UserRequest): ResponseEntity<List<SubredditResponse>>{
        try {
            return ResponseEntity.ok(subredditService.getAllSubredditsByUser(userRequest.toUserEntity()))
        }catch (e:Exception){
            print(e.message)
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}