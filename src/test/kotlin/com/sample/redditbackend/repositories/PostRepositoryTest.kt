package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.PostEntity
import com.sample.redditbackend.entities.SubredditEntity
import com.sample.redditbackend.entities.UserEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var subredditRepository: SubredditRepository

    /** Repository tests
     * save post
     * get post by id
     * get post by heading
     * upvote/downvote post, found by id
     * get All posts of subreddit, Pageable
     * get All posts by User id, Pageable
     */

    final val userEntity = UserEntity(
        userName = "name",
        userDesc = "desc",
        userEmail = "email",
        userPassword = "password"
    )

    final val userEntity1 = UserEntity(
        userName = "name1",
        userDesc = "desc",
        userEmail = "email1",
        userPassword = "password1"
    )

    final val subreddit = SubredditEntity(
        subredditName = "subreddit",
        subredditImage = "image",
        subredditDesc = "Desc",
        user = userEntity
    )
    final val subreddit1 = SubredditEntity(
        subredditName = "subreddit1",
        subredditImage = "image3",
        subredditDesc = "Desc2",
        user = userEntity
    )

    val subredditList = listOf(
        subreddit,
        subreddit1
    )

    final val post = PostEntity(
        postText = "post",
        postImage = "image",
        upvoteCount = 0,
        user = userEntity,
        subredditEntity = subreddit,
        postHeading = "Heading"
    )

    final val postList = listOf(
        post,
        PostEntity(
            postText = "Tame post",
            postImage = "image",
            upvoteCount = 0,
            user = userEntity,
            subredditEntity = subreddit,
            postHeading = "What a post"
        )
    )

    @Test
    fun savePost(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        val savedPost = postRepository.save(post)
        Assertions.assertEquals(savedPost, post)
    }

    @Test
    fun getPost(){
        userRepository.saveAll(listOf(userEntity, userEntity1))
        subredditRepository.saveAll(subredditList)
        val savedPost = postRepository.saveAll(postList)
        val getPost = postRepository.findById(savedPost[0].id).get()
        Assertions.assertEquals(savedPost.first(), getPost)
        Assertions.assertEquals(getPost, post)
    }
    @Test
    fun getPostByHeading(){
        userRepository.saveAll(listOf(userEntity, userEntity1))
        subredditRepository.saveAll(subredditList)
        postRepository.saveAll(postList)
        val getPage = postRepository.findAllByPostHeadingContainingIgnoreCase(post.postHeading, Pageable.ofSize(5))

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, post)
        Assertions.assertEquals(getPage.content.size, 1)
    }

    @Test
    fun getAllPostsBySubreddit(){
        userRepository.saveAll(listOf(userEntity, userEntity1))
        subredditRepository.saveAll(subredditList)
        postRepository.saveAll(postList)
        val getPage = postRepository.findAllBySubredditEntitySubredditNameContainingIgnoreCase(subreddit.subredditName, Pageable.ofSize(5))

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, post)
        Assertions.assertEquals(getPage.content.size, 2)
    }
    @Test
    fun getAllPostsByUser(){
        userRepository.saveAll(listOf(userEntity, userEntity1))
        subredditRepository.saveAll(subredditList)
        postRepository.saveAll(postList)
        val getPage = postRepository.findAllByUserIdContaining(userEntity.id, Pageable.ofSize(5))

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, postList.first())
        Assertions.assertEquals(getPage.content.size, 2)
    }
}