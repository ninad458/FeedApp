package com.example.feedapp

import com.example.feedapp.datasource.Friend
import com.example.feedapp.datasource.Post

interface RetrofitService {
    // fetch post api 
    // /posts/
    suspend fun getPosts(): List<Post>

    // /friends/
    suspend fun getFriends(): List<Friend>
}