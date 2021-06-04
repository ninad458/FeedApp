package com.example.feedapp.datasource.remote

import com.example.feedapp.datasource.model.Friend
import com.example.feedapp.datasource.model.Post

interface RetrofitService {
    // fetch post api 
    // /posts/
    suspend fun getPosts(): List<Post>

    // /friends/
    suspend fun getFriends(): List<Friend>
}