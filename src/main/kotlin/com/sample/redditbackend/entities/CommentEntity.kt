package com.sample.redditbackend.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.PrimaryKeyJoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "comment")
open class CommentEntity(
    open var commentText: String,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    open var parentPost: PostEntity,

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: UserEntity,

    @Column(nullable = false)
    open var upvoteCount: Int
) : BaseEntity()