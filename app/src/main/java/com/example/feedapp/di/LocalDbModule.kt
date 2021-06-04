package com.example.feedapp.di

import android.content.Context
import androidx.room.Room
import com.example.feedapp.localdb.DATABASE_NAME
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
object LocalDbModule {

    private val mDBName =
        DATABASE_NAME

    @Provides
    fun provideDatabase(@ApplicationContext mContext: Context): FeedDatabase {
        return Room.databaseBuilder(
            mContext,
            FeedDatabase::class.java,
            mDBName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProductDao(db: FeedDatabase): FriendDao {
        return db.getFriendDao()
    }

    @Provides
    fun provideStoreDao(db: FeedDatabase): PostDao {
        return db.getPostDao()
    }

    /**
     * [fallbackToDestructiveMigration] :: when we increase the version number of database we have to tell room
     * how to migrate to the new schema  and if don't do this and increase version number our app will actually crash
     *  and we get illegalStateException.
     * By using [fallbackToDestructiveMigration] we can avoid the above exception because it will delete the database and
     *  all its' tables and create the fresh database from the scratch
     * */

}