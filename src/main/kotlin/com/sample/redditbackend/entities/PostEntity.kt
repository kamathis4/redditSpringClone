package com.sample.redditbackend.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post")
open class PostEntity(
    open var postHeading:String,

    open var postText:String,

    @OneToOne(optional = false)
    open var user:UserEntity,

    @Column(nullable = false)
    open var postImage:String,

    @ManyToOne
    @JoinColumn(name = "subreddit_id", nullable = false)
    @JsonManagedReference
    open var subredditEntity: SubredditEntity,

    @Column(nullable = false)
    open var upvoteCount: Int
): BaseEntity()