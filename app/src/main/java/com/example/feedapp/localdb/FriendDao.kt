package com.example.feedapp.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feedapp.datasource.Friend

@Dao
interface FriendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriend(vararg friend: Friend)

    @Query("select * from friend")
    fun fetchFriends():LiveData<List<Friend>>

}