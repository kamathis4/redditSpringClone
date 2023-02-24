package com.sample.redditbackend.services.subreddit

import com.sample.redditbackend.dtos.subreddits.SubredditRequest
import com.sample.redditbackend.dtos.subreddits.SubredditResponse
import com.sample.redditbackend.entities.UserEntity

interface SubredditService {

    fun addSubreddit(subredditRequest: SubredditRequest): SubredditResponse

    fun getSubreddit(subredditId: String): SubredditResponse

    fun getSubredditByName(subredditName: String): List<SubredditResponse>

    fun getAllSubredditsByUser(userId:String): List<SubredditResponse>
}