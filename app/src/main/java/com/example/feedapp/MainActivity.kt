package com.example.feedapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = FeedAdapter()
        val listFeed = findViewById<RecyclerView>(R.id.list_feed)
        listFeed.adapter = adapter
        listFeed.layoutManager = LinearLayoutManager(this)
        viewModel.feed.observe(this, Observer {
            adapter.setData(it)
        })
        viewModel.fetchFeedData()
    }
}