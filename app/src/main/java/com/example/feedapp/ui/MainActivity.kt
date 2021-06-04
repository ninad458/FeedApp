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

    private var count = 0;
    private val viewModel: MainViewModel by viewModels()
    private val mAdapter = FeedAdapter()

    private lateinit var binding: ActivityMainBinding

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
            ++count
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

    private fun getPost(count: Int): List<Post> {
        var c = count
        return listOf(
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
            Post(data = "Post ${++c}"),
        )
    }

    private fun getFriends(count: Int): List<Friend> {
        var c = count
        return listOf(
            Friend(name = "${++c} Raj"),
            Friend(name = "${++c} Vijay"),
            Friend(name = "${++c} Ravi"),
            Friend(name = "${++c} Nitin"),
            Friend(name = "${++c} Hemant"),
            Friend(name = "${++c} Lovely"),
            Friend(name = "${++c} Victor"),
        )
    }
}