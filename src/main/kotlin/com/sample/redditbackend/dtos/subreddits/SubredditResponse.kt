package com.sample.redditbackend.dtos.subreddits

import com.fasterxml.jackson.annotation.JsonInclude
import com.sample.redditbackend.utils.ExceptionUtils
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class SubredditResponse(
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var subredditId:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var subredditName:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var subredditDesc: String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var subredditImage:String,
)