package com.example.feedapp.di

import androidx.annotation.WorkerThread
import com.example.feedapp.datasource.model.Friend
import com.example.feedapp.datasource.model.Post
import com.example.feedapp.datasource.remote.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitService(): RetrofitService {
        return object : RetrofitService {
            @WorkerThread
            override suspend fun getPosts(): List<Post> {
                delay(3000)
                return listOf("Post 1", "Post 2", "Post 3", "Post 4").map { Post(data = it) }
            }

            @WorkerThread
            override suspend fun getFriends(): List<Friend> {
                delay(1000)
                return listOf(
                    "Friend 1",
                    "Friend 2",
                    "Friend 3",
                    "Friend 4",
                    "Friend 5"
                ).map { Friend(name = it) }
            }
        }
    }
}