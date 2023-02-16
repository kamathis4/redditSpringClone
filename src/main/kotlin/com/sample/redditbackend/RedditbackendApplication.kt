package com.sample.redditbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedditbackendApplication

fun main(args: Array<String>) {
    runApplication<RedditbackendApplication>(*args)
}


/** TODO
 * subreddit -> findAllByUser use only user id
 * post -> getPostBySubredditName use only subreddit id, getPostByUserId don't use ignore case for id
 */