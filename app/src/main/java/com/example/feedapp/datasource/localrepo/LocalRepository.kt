package com.example.feedapp.datasource.localrepo

import com.example.feedapp.datasource.Friend
import com.example.feedapp.datasource.Post
import com.example.feedapp.localdb.FriendDao
import com.example.feedapp.localdb.PostDao
import javax.inject.Inject

class LocalRepository @Inject constructor(private val friendDao: FriendDao, private val postDao: PostDao) {

    suspend fun insertFriend(vararg friend: Friend) {

        friendDao.insertFriend(*friend)
    }

    fun getFriend() = friendDao.fetchFriends()

    suspend fun insertPost(vararg post: Post) {

        postDao.insertPost(*post)
    }

    fun getPost() = postDao.fetchPost()

}