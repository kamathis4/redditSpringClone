package com.sample.redditbackend.utils

object Endpoints {
    const val id = "id"
    const val name = "name"

    object Comment{
        const val comment ="/comment"

        const val saveComment = "/"
        const val getComment = "/$id"
        const val upvoteComment ="/upvote/$id"
        const val downvoteComment ="/downvote/$id"
        const val getCommentsByPost = "/byPost/$id"
        const val getCommentsByUser = "/byUser/$id"
    }
    object Post{
        const val post = "/post"

        const val savePost = "/"
        const val getPost = "/$id"
        const val getPostByName = "/$name"
        const val upvotePost ="/upvote/$id"
        const val downvotePost ="/downvote/$id"
        const val getPostBySubreddit = "/bySubreddit/$id"
        const val getPostByUser = "/byUser/$id"
    }
    object Subreddit{
        const val subreddit ="/subreddit"

        const val saveSubreddit = "/"
        const val getSubreddit = "/$id"
        const val getSubredditByName = "/$name"
        const val getSubredditByUser = "/byUser/$id"
    }
    object User{
        const val user ="/user"

        const val saveUser = "/"
        const val getUser = "/$id"
        const val getByName = "/$name"
    }
}