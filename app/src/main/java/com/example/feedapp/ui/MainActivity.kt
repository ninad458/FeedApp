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
import kotlin.random.Random

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
            viewModel.insertFriends(*getFriends().toTypedArray())
            viewModel.insertPost(*getPosts().toTypedArray())
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

    private fun getPosts(): List<Post> {
        return listOf(
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
            Post(data = "Post ${++count}"),
        )
    }

    private fun getFriends(): List<Friend> {
        val name = { randomAlphaNumericString() }
        return listOf(
            Friend(name = name()),
            Friend(name = name()),
            Friend(name = name()),
            Friend(name = name()),
            Friend(name = name()),
            Friend(name = name()),
            Friend(name = name()),
        )
    }

    private fun randomAlphaNumericString(desiredStrLength: Int = 20): String {
        val charPool: List<Char> = ('a'..'z').toList()
        Random.nextInt(20)
        return (1..Random.nextInt(desiredStrLength))
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("").replaceFirstChar { it.uppercaseChar() }
    }
}