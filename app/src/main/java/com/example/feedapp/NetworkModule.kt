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
            override suspend fun getPosts(): List<String> {
                delay(3000)
                return listOf("Post 1", "Post 2", "Post 3", "Post 4")
            }

            @WorkerThread
            override suspend fun getFriends(): List<String> {
                delay(5000)
                return listOf("Friend 1", "Friend 2", "Friend 3", "Friend 4", "Friend 5")
            }
        }
    }
}