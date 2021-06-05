package com.example.feedapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.feedapp.adapter.FeedAdapter
import com.example.feedapp.databinding.ActivityMainBinding
import com.example.feedapp.datasource.Friend
import com.example.feedapp.datasource.Post
import com.example.feedapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mAdapter = FeedAdapter()
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRv()
        observeData()
        addDataToLocal()

    }

    private fun observeData() {

        viewModel.feed.observe(this) {

            mAdapter.setData(it)
        }
    }

    private fun addDataToLocal() {

        binding.addBtn.setOnClickListener {

            viewModel.insertFriends(*getFriends().toTypedArray())
            viewModel.insertPost(*getPost().toTypedArray())
        }
    }

    private fun getPost(): List<Post> {

        return listOf(
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
            Post(text = "Post ${count++}"),
        )
    }

    private fun getFriends(): List<Friend> {

        return listOf(
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
            Friend(name = "Name ${count++}"),
        )
    }

    private fun setRv() {

        binding.listFeed.apply {

            adapter = mAdapter
        }
    }

}