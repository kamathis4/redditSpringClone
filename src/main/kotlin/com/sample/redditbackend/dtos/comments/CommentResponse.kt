package com.sample.redditbackend.dtos.comments

import com.fasterxml.jackson.annotation.JsonInclude
import com.sample.redditbackend.utils.ExceptionUtils
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class CommentResponse (
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var commentId:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var commentText:String
)