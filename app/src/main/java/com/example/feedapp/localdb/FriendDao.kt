package com.example.feedapp.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feedapp.datasource.model.Friend
@Dao
interface FriendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertFriend(vararg friend: Friend):List<Long>

    @Query("select * From friend")
    fun  fetchFriend() : LiveData<List<Friend>>
}
