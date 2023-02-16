package com.sample.redditbackend.mappers

import com.sample.redditbackend.dtos.subreddits.SubredditRequest
import com.sample.redditbackend.dtos.subreddits.SubredditResponse
import com.sample.redditbackend.entities.SubredditEntity
import com.sample.redditbackend.entities.UserEntity

fun SubredditRequest.toSubredditEntity(userEntity: UserEntity): SubredditEntity {
    return SubredditEntity(
        subredditName = this.subredditName,
        subredditDesc = this.subredditDesc,
        subredditImage = this.subredditImage,
        user = userEntity
    )
}

fun SubredditEntity.toSubredditResponse(): SubredditResponse {
    return SubredditResponse(
        subredditId = this.id,
        subredditName = this.subredditName,
        subredditDesc = this.subredditDesc,
        subredditImage = this.subredditImage
    )
}