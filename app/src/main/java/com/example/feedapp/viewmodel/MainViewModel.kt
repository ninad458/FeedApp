package com.example.feedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedapp.datasource.local.LocalRepository
import com.example.feedapp.datasource.model.FeedListItem
import com.example.feedapp.datasource.model.Friend
import com.example.feedapp.datasource.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val localRepo: LocalRepository) : ViewModel() {

    private val friendsLiveData = localRepo.getFriends()

    private val postsLiveData = localRepo.getPosts()

    val feed: LiveData<List<FeedListItem>> = MediatorLiveData<List<FeedListItem>>().apply {

        fun handleData() {
            val posts = postsLiveData.value ?: return
            val friends = friendsLiveData.value
            val feedList = posts.map { FeedListItem.PostType(it) }.toMutableList<FeedListItem>()

            if (!friends.isNullOrEmpty()) {
                feedList.add(0, FeedListItem.FriendsType(friends))
            }

            postValue(feedList)
        }

        addSource(postsLiveData) {
            handleData()
        }
        addSource(friendsLiveData) {
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

}