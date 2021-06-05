package com.example.feedapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feedapp.datasource.FeedListItem
import com.example.feedapp.datasource.FeedListItem.FriendsType
import com.example.feedapp.datasource.FeedListItem.PostType
import com.example.feedapp.R.id
import com.example.feedapp.R.layout

private const val VH_POST = 2
private const val VH_FRIEND = 1

class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var feed: List<FeedListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VH_POST -> PostView(
                layoutInflater.inflate(layout.row_item_post, parent, false)
            )
            VH_FRIEND -> FriendsView(
                layoutInflater
                    .inflate(layout.row_item_friends, parent, false)
            )
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feedListItem = feed[position]
        if (holder is PostView && feedListItem is PostType) {
            holder.bindPost(feedListItem)
        } else if (holder is FriendsView && feedListItem is FriendsType) {
            holder.bindFriends(feedListItem)
        } else throw IllegalStateException()
    }

    override fun getItemCount(): Int {
        return feed.size
    }

    fun setData(feed: List<FeedListItem>) {
        this.feed = feed
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (feed[position]) {
            is PostType -> VH_POST
            is FriendsType -> VH_FRIEND

        }
    }
}

class PostView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindPost(postType: PostType) {
        itemView.findViewById<TextView>(id.text_post).text = postType.post.text
    }
}

class FriendsView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindFriends(friendsType: FriendsType) {
        itemView.findViewById<TextView>(id.list_friends).text =
            friendsType.friends.joinToString { it.name }
    }
}