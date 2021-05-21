package com.example.feedapp

interface RetrofitService {
    suspend fun getPosts(): List<String>
    suspend fun getFriends(): List<String>
}