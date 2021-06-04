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

        val friendList = listOf<Friend>(

            Friend(name = "$count Raj"),
            Friend(name = "$count Vijay"),
            Friend(name = "$count Ravi"),
            Friend(name = "$count Nitin"),
            Friend(name = "$count Hemant"),
            Friend(name = "$count Lovely"),
            Friend(name = "$count Victor"),
        )

        val postList = listOf<Post>(

            Post(data = "$count Post1"),
            Post(data = "$count Post2"),
            Post(data = "$count Post3"),
            Post(data = "$count Post4"),
            Post(data = "$count Post5"),
            Post(data = "$count Post6"),
            Post(data = "$count Post7"),
        )
        binding.addFab.setOnClickListener {
            viewModel.insertFriends(*friendList.toTypedArray())
            viewModel.insertPost(*postList.toTypedArray())
            count+=1;
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
}