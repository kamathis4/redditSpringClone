package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.PostEntity
import com.sample.redditbackend.entities.SubredditEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository:JpaRepository<PostEntity, String> {

    fun findAllByPostHeadingContainingIgnoreCase(postHeading:String, pageable: Pageable):Page<PostEntity>

    fun findAllBySubredditEntitySubredditNameContaining(subredditName: String, pageable: Pageable):Page<PostEntity>
}