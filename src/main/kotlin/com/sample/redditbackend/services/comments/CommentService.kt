package com.sample.redditbackend.services.comments

import com.sample.redditbackend.dtos.comments.CommentRequest
import com.sample.redditbackend.dtos.comments.CommentResponse
import com.sample.redditbackend.dtos.posts.PostResponse

interface CommentService {

    fun saveComment(commentRequest: CommentRequest):CommentResponse

    fun getComment(commentId:String):CommentResponse

    fun getAllCommentsByPost(postId:String):List<CommentResponse>

    fun getAllCommentByUser(userId:String):List<CommentResponse>

    fun upvoteComment(commentId: String):CommentResponse

    fun downvoteComment(commentId: String):CommentResponse
}