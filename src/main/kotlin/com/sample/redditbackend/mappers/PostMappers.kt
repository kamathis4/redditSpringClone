package com.sample.redditbackend.mappers

import com.sample.redditbackend.dtos.posts.PostRequest
import com.sample.redditbackend.dtos.posts.PostResponse
import com.sample.redditbackend.entities.PostEntity
import com.sample.redditbackend.entities.SubredditEntity
import com.sample.redditbackend.entities.UserEntity

fun PostRequest.toPostEntity(userEntity: UserEntity, subredditEntity: SubredditEntity, upvoteCount: Int): PostEntity {
    return PostEntity(
        postText = this.postText,
        user = userEntity,
        postImage = this.postImage,
        subredditEntity = subredditEntity,
        upvoteCount = upvoteCount,
        postHeading = this.postHeading
    )
}

fun PostEntity.toPostResponse(noOfComments:Int): PostResponse {
    return PostResponse(
        postId = this.id,
        postText = this.postText,
        postImage = this.postImage,
        noOfUpvotes = this.upvoteCount,
        noOfComments = noOfComments,
        postHeading = this.postHeading
    )
}