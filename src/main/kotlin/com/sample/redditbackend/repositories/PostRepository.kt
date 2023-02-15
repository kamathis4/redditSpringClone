package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository:JpaRepository<PostEntity, String> {
}