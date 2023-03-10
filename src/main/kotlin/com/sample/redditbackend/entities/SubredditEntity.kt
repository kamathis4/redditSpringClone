package com.sample.redditbackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "subreddit")
open class SubredditEntity(
    open var subredditName: String,
    open var subredditDesc: String,
    open var subredditImage: String,

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    open var user:UserEntity
    ) : BaseEntity()