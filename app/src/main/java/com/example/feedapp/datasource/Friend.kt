package com.example.feedapp.datasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    @ColumnInfo(name="name")
    val name: String
    )
