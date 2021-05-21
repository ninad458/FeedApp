package com.example.feedapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchFeed()
        val feedListView = findViewById<RecyclerView>(R.id.list_feed)
        val feedAdapter = FeedAdapter()
        feedListView.adapter = feedAdapter
        feedListView.layoutManager = LinearLayoutManager(this)

        viewModel.feed.observe(this, { feed ->
            feedAdapter.setData(feed)
        })
    }
}