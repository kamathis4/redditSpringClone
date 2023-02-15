package com.sample.redditbackend.dtos.users

import com.fasterxml.jackson.annotation.JsonInclude
import com.sample.redditbackend.utils.ExceptionUtils
import com.sample.redditbackend.utils.ExceptionUtils.NOT_EMPTY
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserResponse (
    @field: NotNull
    var userId: String,
    @field: NotNull
    @NotEmpty
    var userName:String,
    @field: NotNull
    @NotEmpty
    @Email
    var userEmail:String,
    @field: NotNull
    @NotEmpty
    var userDesc:String,
)