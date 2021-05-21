package com.example.feedapp

sealed class FeedListItem {
    data class PostType(val text: String) : FeedListItem()
    data class FriendsType(val friends: List<String>) : FeedListItem()
}