package com.sample.redditbackend.mappers

import com.sample.redditbackend.dtos.comments.CommentRequest
import com.sample.redditbackend.dtos.comments.CommentResponse
import com.sample.redditbackend.entities.CommentEntity
import com.sample.redditbackend.entities.PostEntity
import com.sample.redditbackend.entities.UserEntity

fun CommentRequest.toCommentEntity(postEntity: PostEntity, userEntity: UserEntity, upvoteCount: Int): CommentEntity {
    return CommentEntity(
        commentText = this.commentText,
        parentPost = postEntity,
        user = userEntity,
        upvoteCount = upvoteCount
    )
}

fun CommentEntity.toCommentResponse(): CommentResponse {
    return CommentResponse(
        commentId = this.id,
        commentText = this.commentText,
        commentUpvoteCount = this.upvoteCount
    )
}