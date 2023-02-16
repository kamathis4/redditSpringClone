package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.CommentEntity
import com.sample.redditbackend.entities.PostEntity
import com.sample.redditbackend.entities.SubredditEntity
import com.sample.redditbackend.entities.UserEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    lateinit var commentRepository: CommentRepository
    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var subredditRepository: SubredditRepository

    /** Repository tests
     * save comment
     * get comment by id
     * upvote/downvote comment, found by id
     * get All comments of post, Pageable
     * get All comments by User, Pageable
     */

    final val userEntity = UserEntity(
        userName = "name",
        userDesc = "desc",
        userEmail = "email",
        userPassword = "password"
    )
    final val subreddit = SubredditEntity(
        subredditName = "subreddit",
        subredditImage = "image",
        subredditDesc = "Desc",
        user = userEntity
    )
    final val post = PostEntity(
        postText = "post",
        postImage = "image",
        upvoteCount = 0,
        user = userEntity,
        subredditEntity = subreddit,
        postHeading = "Heading"
    )
    final val comment = CommentEntity(
        commentText = "comment",
        parentPost = post,
        user = userEntity,
        upvoteCount = 0
    )

    final val comment1 = CommentEntity(
        commentText = "comment1",
        parentPost = post,
        user = userEntity,
        upvoteCount = 0
    )


    @Test
    fun saveComment(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        postRepository.save(post)
        val savedComment = commentRepository.save(comment)
        Assertions.assertEquals(savedComment, comment)
    }

    @Test
    fun getComment(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        postRepository.save(post)
        val savedComment = commentRepository.save(comment)
        val getComment = commentRepository.findById(savedComment.id).get()

        Assertions.assertEquals(getComment, comment)
        Assertions.assertEquals(savedComment, getComment)
    }

    @Test
    fun getAllCommentsByUser(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        postRepository.save(post)
        commentRepository.saveAll(listOf(comment, comment1))

        val getPage = commentRepository.findAllByUserIdContainingIgnoreCase(userEntity.id, Pageable.ofSize(5))
        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, comment1)
        Assertions.assertEquals(getPage.content.size, 2)
    }

    @Test
    fun getAllCommentsByPost(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        postRepository.save(post)
        commentRepository.saveAll(listOf(comment, comment1))

        val getPage = commentRepository.findAllByParentPostIdContainingIgnoreCase(post.id, Pageable.ofSize(5))
        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, comment1)
        Assertions.assertEquals(getPage.content.size, 2)
    }
}