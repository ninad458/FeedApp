package com.example.feedapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feedapp.adapter.FeedAdapter
import com.example.feedapp.databinding.ActivityMainBinding
import com.example.feedapp.datasource.model.Friend
import com.example.feedapp.datasource.model.Post
import com.example.feedapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val mAdapter = FeedAdapter()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setRv()
        setViewModel()

        addDataToLocal()
    }

    private fun addDataToLocal() {

        val friendList = listOf<Friend>(

            Friend(name = "Raj"),
            Friend(name = "Vijay"),
            Friend(name = "Ravi"),
            Friend(name = "Nitin"),
            Friend(name = "Hemant"),
            Friend(name = "Lovely"),
            Friend(name = "Victor"),
        )

        val postList = listOf<Post>(

            Post(data = "Post1"),
            Post(data = "Post2"),
            Post(data = "Post3"),
            Post(data = "Post4"),
            Post(data = "Post5"),
            Post(data = "Post6"),
            Post(data = "Post7"),
        )
        binding.addFab.setOnClickListener {
            viewModel.insertFriends(*friendList.toTypedArray())
            viewModel.insertPost(Post(data = "Post1"))
        }



    }

    private fun setRv() {

        binding.listFeed.apply {

            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setViewModel() {
        viewModel.feed.observe(this, Observer {
            mAdapter.setData(it)
        })
        viewModel.fetchFeedData()
    }
}