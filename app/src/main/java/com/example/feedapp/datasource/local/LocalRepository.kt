package com.example.feedapp.datasource.local

import com.example.feedapp.datasource.model.Friend
import com.example.feedapp.datasource.model.Post
import com.example.feedapp.localdb.FriendDao
import com.example.feedapp.localdb.PostDao
import javax.inject.Inject

class LocalRepository @Inject constructor(private val friendDao: FriendDao, private val postDao: PostDao) {

    suspend fun insertFriend(vararg friend: Friend) {
        friendDao.insertFriend(*friend)
    }

    fun getFriends() = friendDao.fetchFriend()

    suspend fun insertPost(vararg post: Post) {
        postDao.insertPost(*post)
    }

    fun getPosts() = postDao.fetchPost()
}