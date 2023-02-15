package com.sample.redditbackend.dtos.subreddits

import com.fasterxml.jackson.annotation.JsonInclude
import com.sample.redditbackend.utils.ExceptionUtils
import com.sample.redditbackend.utils.ExceptionUtils.MAX_LENGTH
import com.sample.redditbackend.utils.ExceptionUtils.MAX_LENGTH_DESC
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class SubredditRequest(
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    @Max(value = MAX_LENGTH)
    var subredditName:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    @Max(value = MAX_LENGTH_DESC)
    var subredditDesc: String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var subredditImage:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var userId:String
)