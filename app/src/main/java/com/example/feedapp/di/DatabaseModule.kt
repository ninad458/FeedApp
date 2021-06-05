package com.example.feedapp.di

import android.content.Context
import androidx.room.Room
import com.example.feedapp.localdb.DB_NAME
import com.example.feedapp.localdb.FeedDatabase
import com.example.feedapp.localdb.FriendDao
import com.example.feedapp.localdb.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext mContext: Context): FeedDatabase {

        return Room.databaseBuilder(mContext, FeedDatabase::class.java, DB_NAME).build()
    }

    @Provides
    fun providesFriendDao(db: FeedDatabase): FriendDao {

        return db.getFriendDao()
    }

    @Provides
    fun providesPostDao(db: FeedDatabase): PostDao {

        return db.getPostDao()
    }

}