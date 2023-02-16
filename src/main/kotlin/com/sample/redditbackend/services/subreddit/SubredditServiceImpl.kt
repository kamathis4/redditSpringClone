package com.sample.redditbackend.services.subreddit

import com.sample.redditbackend.dtos.subreddits.SubredditRequest
import com.sample.redditbackend.dtos.subreddits.SubredditResponse
import com.sample.redditbackend.entities.UserEntity
import com.sample.redditbackend.mappers.toSubredditEntity
import com.sample.redditbackend.mappers.toSubredditResponse
import com.sample.redditbackend.repositories.SubredditRepository
import com.sample.redditbackend.repositories.UserRepository
import com.sample.redditbackend.utils.SpringRequiredUtils.PAGE_SIZE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors.toList
@Service
class SubredditServiceImpl : SubredditService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var subredditRepository: SubredditRepository


    override fun addSubreddit(subredditRequest: SubredditRequest): SubredditResponse {
        try {
            val userId = subredditRequest.userId
            val userEntity = userRepository.findById(userId).orElseThrow { Exception("Couldn't get User") }
            val subreddit = subredditRequest.toSubredditEntity(userEntity)
            val savedSubreddit = subredditRepository.save(subreddit)
            return savedSubreddit.toSubredditResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getSubreddit(subredditId: String): SubredditResponse {
        try {
            val subreddit =
                subredditRepository.findById(subredditId).orElseThrow { Exception("Couldn't get subreddit") }
            return subreddit.toSubredditResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getSubredditByName(subredditName: String): List<SubredditResponse> {
        try {
            return subredditRepository.findAllBySubredditNameContainingIgnoreCase(
                subredditName,
                Pageable.ofSize(PAGE_SIZE)
            ).stream().map { subredditEntity ->
                subredditEntity.toSubredditResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getAllSubredditsByUser(userEntity: UserEntity): List<SubredditResponse> {
        try {
            return subredditRepository.findAllByUser(userEntity)
                .stream().map { subredditEntity ->
                subredditEntity.toSubredditResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }
}