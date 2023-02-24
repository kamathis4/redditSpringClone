package com.sample.redditbackend

import com.sample.redditbackend.config.SwaggerConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig::class)
class RedditbackendApplication

fun main(args: Array<String>) {
    runApplication<RedditbackendApplication>(*args)
}


/** TODO
 * Change image storage to folder
 * Check all endpoints
 */