package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.SubredditEntity
import com.sample.redditbackend.entities.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubredditRepository:JpaRepository<SubredditEntity, String> {

    fun findAllBySubredditNameContainingIgnoreCase(subredditName: String, pageable: Pageable):Page<SubredditEntity>

    fun findAllByUserIdContaining(id:String):List<SubredditEntity>
}