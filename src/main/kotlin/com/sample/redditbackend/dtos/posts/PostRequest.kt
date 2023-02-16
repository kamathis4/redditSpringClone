package com.sample.redditbackend.dtos.posts

import com.fasterxml.jackson.annotation.JsonInclude
import com.sample.redditbackend.utils.ExceptionUtils
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class PostRequest (
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var userID:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var subredditId:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var postHeading:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var postText:String,
    @field: NotNull
    var postImage:String
)