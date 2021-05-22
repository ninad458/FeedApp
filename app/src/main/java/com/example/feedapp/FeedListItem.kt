package com.example.feedapp

sealed class FeedListItem {
    data class PostType(val post: Post) : FeedListItem()
    data class FriendsType(val friends: List<Friend>) : FeedListItem()
}