package com.sample.redditbackend.dtos.users

import com.fasterxml.jackson.annotation.JsonInclude
import com.sample.redditbackend.utils.ExceptionUtils
import com.sample.redditbackend.utils.ExceptionUtils.MAX_LENGTH
import com.sample.redditbackend.utils.ExceptionUtils.MAX_LENGTH_EXCEEDED
import com.sample.redditbackend.utils.ExceptionUtils.REGEXP_PASSWORD_MATCHER
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import org.jetbrains.annotations.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserRequest (
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    @Max(value = MAX_LENGTH, message = MAX_LENGTH_EXCEEDED)
    var userName:String,
    @field: NotNull
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    @Email
    var userEmail:String,
    @field: NotNull
    @Pattern(regexp = REGEXP_PASSWORD_MATCHER)
    @NotEmpty(message = ExceptionUtils.NOT_EMPTY)
    var userPassword:String
)