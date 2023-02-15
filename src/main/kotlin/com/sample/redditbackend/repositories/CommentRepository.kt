package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository:JpaRepository<CommentEntity, String> {
}