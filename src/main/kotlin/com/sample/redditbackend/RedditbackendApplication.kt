package com.sample.redditbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedditbackendApplication

fun main(args: Array<String>) {
    runApplication<RedditbackendApplication>(*args)
}
