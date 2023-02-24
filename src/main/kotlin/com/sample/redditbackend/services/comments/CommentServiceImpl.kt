package com.sample.redditbackend.services.comments

import com.sample.redditbackend.dtos.comments.CommentRequest
import com.sample.redditbackend.dtos.comments.CommentResponse
import com.sample.redditbackend.dtos.posts.PostResponse
import com.sample.redditbackend.mappers.toCommentEntity
import com.sample.redditbackend.mappers.toCommentResponse
import com.sample.redditbackend.repositories.CommentRepository
import com.sample.redditbackend.repositories.PostRepository
import com.sample.redditbackend.repositories.UserRepository
import com.sample.redditbackend.utils.SpringRequiredUtils.PAGE_SIZE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.toList

@Service
class CommentServiceImpl : CommentService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var commentRepository: CommentRepository


    @Transactional
    override fun saveComment(commentRequest: CommentRequest): CommentResponse {
        try {
            val userId = commentRequest.userId
            val postId = commentRequest.postId
            val user = userRepository.findById(userId).orElseThrow { Exception("User not found") }
            val post = postRepository.findById(postId).orElseThrow { Exception("Post not found") }
            val comment = commentRequest.toCommentEntity(post, user, 0)
            val savedComment = commentRepository.save(comment)
            return savedComment.toCommentResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getComment(commentId: String): CommentResponse {
        try {
            val comment = commentRepository.findById(commentId).orElseThrow { Exception("Comment not found") }
            return comment.toCommentResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getAllCommentsByPost(postId: String): List<CommentResponse> {
        try {
            val commentPage =
                commentRepository.findAllByParentPostIdContainingIgnoreCase(postId, Pageable.ofSize(PAGE_SIZE))
            return commentPage.stream().map { commentEntity ->
                commentEntity.toCommentResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getAllCommentByUser(userId: String): List<CommentResponse> {
        try {
            val commentPage =
                commentRepository.findAllByUserIdContainingIgnoreCase(userId, Pageable.ofSize(PAGE_SIZE))
            return commentPage.stream().map { commentEntity ->
                commentEntity.toCommentResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun upvoteComment(commentId: String): CommentResponse {
        try {
            val comment = commentRepository.findById(commentId).orElseThrow { Exception("Comment not found") }
            comment.upvoteCount += 1
            return commentRepository.save(comment).toCommentResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun downvoteComment(commentId: String): CommentResponse {
        try {
            val comment = commentRepository.findById(commentId).orElseThrow { Exception("Comment not found") }
            comment.upvoteCount -= 1
            return commentRepository.save(comment).toCommentResponse()
        } catch (e: Exception) {
            throw e
        }
    }
}