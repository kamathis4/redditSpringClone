package com.sample.redditbackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "subreddit")
open class SubredditEntity(
    open var subredditName: String,
    open var subredditDesc: String,
    open var subredditImage: String,
    ) : BaseEntity()