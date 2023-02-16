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
     * get All posts by User, Pageable
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
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        val savedPost = postRepository.save(post)
        val getPost = postRepository.findById(savedPost.id).get()
        Assertions.assertEquals(savedPost, getPost)
        Assertions.assertEquals(getPost, post)
    }
    @Test
    fun getALLPost(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        val savedPost = postRepository.saveAll(postList)
        val getPost = postRepository.findAllById(listOf(savedPost.first().id, savedPost.last().id)).toList()
//        Assertions.assertEquals(savedPost, getPost)
        Assertions.assertEquals(getPost.size, 2)
    }
    @Test
    fun getPostByHeading(){
        postRepository.flush()
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        postRepository.saveAll(postList)
        val getPage = postRepository.findAllByPostHeadingContainingIgnoreCase(post.postHeading, Pageable.ofSize(5))

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, post)
        Assertions.assertEquals(getPage.content.size, 1)
    }

    @Test
    fun getAllPostsBySubreddit(){
        userRepository.save(userEntity)
        subredditRepository.save(subreddit)
        postRepository.saveAll(postList)
        val getPage = postRepository.findAllBySubredditEntitySubredditNameContaining(subreddit.subredditName, Pageable.ofSize(5))

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, post)
        Assertions.assertEquals(getPage.content.size, 2)

    }
}