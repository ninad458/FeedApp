package com.example.feedapp

interface RetrofitService {
    // fetch post api 
    // /posts/
    suspend fun getPosts(): List<Post>

    // /friends/
    suspend fun getFriends(): List<Friend>
}