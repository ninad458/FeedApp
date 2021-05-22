package com.example.feedapp

import androidx.annotation.WorkerThread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.delay

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitService(): RetrofitService {
        return object : RetrofitService {
            @WorkerThread
            override suspend fun getPosts(): List<Post> {
                delay(3000)
                return listOf("Post 1", "Post 2", "Post 3", "Post 4").map { Post(it) }
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
                ).map { Friend(it) }
            }
        }
    }
}