package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, String> {

    fun findAllByPostHeadingContainingIgnoreCase(postHeading: String, pageable: Pageable): Page<PostEntity>

    fun findAllBySubredditEntitySubredditNameContainingIgnoreCase(
        subredditName: String,
        pageable: Pageable
    ): Page<PostEntity>

    fun findAllByUserIdContaining(id: String, pageable: Pageable): Page<PostEntity>
}