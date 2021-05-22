package com.example.feedapp

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val service: RetrofitService) : ViewModel() {

    private val friendsLiveData = MutableLiveData<List<Friend>>()

    private val postsLiveData = MutableLiveData<List<Post>>()

    val feed: LiveData<List<FeedListItem>> = MediatorLiveData<List<FeedListItem>>().apply {

        fun handleData() {
            val posts = postsLiveData.value ?: return
            val friends = friendsLiveData.value
            val feedList = posts.map { FeedListItem.PostType(it) }.toMutableList<FeedListItem>()

            if (friends != null) {
                feedList.add(1, FeedListItem.FriendsType(friends))
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

    fun fetchFeedData() {
        getFriendsAsync()
        getPostsAsync()
    }

    private fun getPostsAsync() {
        viewModelScope.launch {
            val posts = service.getPosts()
            postsLiveData.value = posts
        }
    }

    private fun getFriendsAsync() {
        viewModelScope.launch {
            val friends = service.getFriends()
            friendsLiveData.value = friends
        }
    }
}