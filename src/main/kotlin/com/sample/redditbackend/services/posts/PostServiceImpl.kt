package com.sample.redditbackend.services.posts

import com.sample.redditbackend.dtos.posts.PostRequest
import com.sample.redditbackend.dtos.posts.PostResponse
import com.sample.redditbackend.mappers.toPostEntity
import com.sample.redditbackend.mappers.toPostResponse
import com.sample.redditbackend.repositories.PostRepository
import com.sample.redditbackend.repositories.SubredditRepository
import com.sample.redditbackend.repositories.UserRepository
import com.sample.redditbackend.utils.SpringRequiredUtils.PAGE_SIZE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.toList
@Service
class PostServiceImpl : PostService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var subredditRepository: SubredditRepository

    @Autowired
    lateinit var postRepository: PostRepository


    override fun savePost(postRequest: PostRequest): PostResponse {
        try {
            val userId = postRequest.userID
            val subredditId = postRequest.subredditId
            val user = userRepository.findById(userId).orElseThrow { Exception("Unable to find User") }
            val subreddit =
                subredditRepository.findById(subredditId).orElseThrow { Exception("Unable to find Subreddit") }
            val post = postRequest.toPostEntity(user, subreddit, 0)
            val savedPost = postRepository.save(post)
            return savedPost.toPostResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getPost(postId: String): PostResponse {
        try {
            val post = postRepository.findById(postId).orElseThrow { Exception("Unable to find post") }
            return post.toPostResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getPostByHeading(postHeading: String): List<PostResponse> {
        try {
            val postPage = postRepository.findAllByPostHeadingContainingIgnoreCase(postHeading, Pageable.ofSize(PAGE_SIZE))
            return postPage.stream().map {postEntity->
                postEntity.toPostResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getPostBySubredditName(subredditName: String): List<PostResponse> {
        try {
            val postPage = postRepository.findAllBySubredditEntitySubredditNameContainingIgnoreCase(subredditName, Pageable.ofSize(PAGE_SIZE))
            return postPage.stream().map {postEntity->
                postEntity.toPostResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getPostByUserId(userId: String): List<PostResponse> {
        try {
            val postPage = postRepository.findAllByUserIdContaining(userId, Pageable.ofSize(PAGE_SIZE))
            return postPage.stream().map {postEntity->
                postEntity.toPostResponse()
            }.collect(toList())
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun upvotePost(postId: String): PostResponse {
        try {
            val post = postRepository.findById(postId).orElseThrow { Exception("Unable to find post") }
            post.upvoteCount+=1
            val savedPost = postRepository.save(post)
            return savedPost.toPostResponse()
        }catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun downvotePost(postId: String): PostResponse {
        try {
            val post = postRepository.findById(postId).orElseThrow { Exception("Unable to find post") }
            post.upvoteCount-=1
            val savedPost = postRepository.save(post)
            return savedPost.toPostResponse()
        }catch (e: Exception) {
            throw e
        }
    }
}