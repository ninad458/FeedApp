package com.example.feedapp.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feedapp.datasource.model.Friend
import com.example.feedapp.datasource.model.Post

const val DATABASE_NAME = "feed_list_db"

@Database(entities = [Friend::class, Post::class], version = 1, exportSchema = false)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun getFriendDao(): FriendDao
    abstract fun getPostDao(): PostDao
}

