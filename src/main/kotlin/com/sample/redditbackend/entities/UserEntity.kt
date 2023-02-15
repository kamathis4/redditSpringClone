package com.sample.redditbackend.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user")
open class UserEntity(

    open var userName: String,

    open var userEmail: String,

    open var userPassword: String,

    open var userDesc: String

) : BaseEntity()