package com.sample.redditbackend.repositories

import com.sample.redditbackend.entities.CommentEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<CommentEntity, String> {
    fun findAllByUserIdContainingIgnoreCase(id: String, pageable: Pageable): Page<CommentEntity>

    fun findAllByParentPostIdContainingIgnoreCase(id: String, pageable: Pageable): Page<CommentEntity>
}