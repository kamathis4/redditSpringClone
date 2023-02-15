package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.SubredditEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubredditRepository:JpaRepository<SubredditEntity, String> {
}