package com.example.feedapp.datasource

sealed class FeedListItem {
    data class PostType(val post: Post) : FeedListItem()
    data class FriendsType(val friends: List<Friend>) : FeedListItem()
}