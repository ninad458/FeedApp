package com.example.feedapp.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feedapp.datasource.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertPost(post: Post) : Long

    @Query("select * From post")
    fun  fetchPost() : LiveData<List<Post>>

}
