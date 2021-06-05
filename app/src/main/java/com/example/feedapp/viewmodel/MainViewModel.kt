package com.example.feedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedapp.datasource.FeedListItem
import com.example.feedapp.datasource.FeedListItem.FriendsType
import com.example.feedapp.datasource.FeedListItem.PostType
import com.example.feedapp.datasource.Friend
import com.example.feedapp.datasource.Post
import com.example.feedapp.datasource.localrepo.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val localRepo: LocalRepository) : ViewModel() {

    private val friendsLiveData = localRepo.getFriend()

    private val postsLiveData = localRepo.getPost()

    val feed: LiveData<List<FeedListItem>> = MediatorLiveData<List<FeedListItem>>().apply {

        fun handleData() {
            val posts = postsLiveData.value ?: return
            val friends = friendsLiveData.value
            val feedList = posts.map { PostType(it) }.toMutableList<FeedListItem>()



            if (!friends.isNullOrEmpty()) {

                val pos = if(feedList.isNotEmpty()) 2 else 0

                feedList.add(pos, FriendsType(friends))
            }

            postValue(feedList)
        }

        addSource(friendsLiveData) {
            handleData()
        }

        addSource(postsLiveData) {
            handleData()
        }
    }

    fun insertFriends(vararg friend: Friend) {

        viewModelScope.launch(Dispatchers.IO) {

            localRepo.insertFriend(*friend)
        }

    }

    fun insertPost(vararg post: Post) {

        viewModelScope.launch(Dispatchers.IO) {

            localRepo.insertPost(*post)
        }

    }

//    fun fetchFeedData() {
//        getFriendsAsync()
//        getPostsAsync()
//    }
//
//    private fun getPostsAsync() {
//        viewModelScope.launch {
//            val posts = localRepo.getPosts()
//            postsLiveData.value = posts
//        }
//    }
//
//    private fun getFriendsAsync() {
//        viewModelScope.launch {
//            val friends = localRepo.getFriends()
//            friendsLiveData.value = friends
//        }
//    }
}