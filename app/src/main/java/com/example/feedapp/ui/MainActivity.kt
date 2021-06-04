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

    var count = 0;
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

        binding.addFab.setOnClickListener {
            count++
            viewModel.insertFriends(*getFriends(count).toTypedArray())
            viewModel.insertPost(*getPost(count).toTypedArray())
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
    }

    fun getPost(count: Int): List<Post> {
        return listOf<Post>(

            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
            Post(data = "Post $count"),
        )
    }

    fun getFriends(count: Int): List<Friend> {
        return listOf<Friend>(

            Friend(name = "$count Raj"),
            Friend(name = "$count Vijay"),
            Friend(name = "$count Ravi"),
            Friend(name = "$count Nitin"),
            Friend(name = "$count Hemant"),
            Friend(name = "$count Lovely"),
            Friend(name = "$count Victor"),
        )
    }
}