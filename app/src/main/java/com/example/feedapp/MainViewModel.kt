package com.example.feedapp

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: RetrofitService) : ViewModel() {

    private val postsLiveData = MutableLiveData<List<String>>()
    private val friendsLiveData = MutableLiveData<List<String>>()

    val feed: LiveData<List<FeedListItem>> = MediatorLiveData<List<FeedListItem>>().apply {
        fun prepareData() {
            val posts = postsLiveData.value ?: return
            val friends = friendsLiveData.value
            val feedData = posts
                .map { FeedListItem.PostType(it) }
                .toMutableList<FeedListItem>()
            if (friends != null && friends.isNotEmpty())
                feedData.add(0, FeedListItem.FriendsType(friends))
            postValue(feedData)
        }

        addSource(postsLiveData) {
            prepareData()
        }
        addSource(friendsLiveData) {
            prepareData()
        }
    }


    fun fetchFeed() {
        viewModelScope.launch {
            val deferredPosts = async { api.getPosts() }
            val deferredFriends = async { api.getFriends() }
            val posts = deferredPosts.await()
            postsLiveData.postValue(posts)
            val friends = deferredFriends.await()
            friendsLiveData.postValue(friends)
        }
    }
}