package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.SubredditEntity
import com.sample.redditbackend.entities.UserEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubredditRepositoryTest {

    /** Repository tests
     * save subreddit
     * get subreddit by id
     * find subreddit containing name, pageable
     * get subredits followed by user  pageable
     */

    @Autowired
    lateinit var subredditRepository: SubredditRepository

    @Autowired
    lateinit var userRepository: UserRepository

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

    val subredditList = listOf(
        subreddit,
        SubredditEntity(
            subredditName = "subreddit1",
            subredditImage = "image3",
            subredditDesc = "Desc2",
            user = userEntity
        )
    )

    @Test
    fun saveSubreddit() {
        userRepository.save(userEntity)
        val savedSubreddit = subredditRepository.save(subreddit)
        Assertions.assertEquals(savedSubreddit, subreddit)
    }

    @Test
    fun getUser() {
        userRepository.save(userEntity)
        val savedSubreddit = subredditRepository.save(subreddit)
        val getSubreddit = subredditRepository.findById(savedSubreddit.id).get()

        Assertions.assertEquals(savedSubreddit, subreddit)
        Assertions.assertEquals(savedSubreddit, getSubreddit)
    }

    @Test
    fun getSubredditByName() {
        userRepository.save(userEntity)
        subredditRepository.saveAll(subredditList)
        val getPage = subredditRepository.findAllBySubredditNameContainingIgnoreCase(
            subreddit.subredditName,
            Pageable.ofSize(5)
        )

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, subreddit)
        Assertions.assertEquals(getPage.content.size, 2)
    }

    @Test
    fun getSubredditByIncompleteSubredditName() {
        userRepository.save(userEntity)
        subredditRepository.saveAll(subredditList)
        val getPage = subredditRepository.findAllBySubredditNameContainingIgnoreCase(
            subreddit.subredditName.dropLast(2),
            Pageable.ofSize(5)
        )

        val item = getPage.get().findFirst().get()
        Assertions.assertEquals(item, subreddit)
        Assertions.assertEquals(getPage.content.size, 2)
    }

    @Test
    fun getSubredditByUserName() {
        userRepository.save(userEntity)
        subredditRepository.saveAll(subredditList)
        val getList = subredditRepository.findAllByUser(userEntity)

        Assertions.assertEquals(getList.size, 2)
        Assertions.assertEquals(getList, subredditList)
    }
}