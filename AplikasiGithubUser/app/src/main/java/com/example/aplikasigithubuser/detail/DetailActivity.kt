package com.example.aplikasigithubuser.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.model.ResponseGithubDetail
import com.example.aplikasigithubuser.databinding.ActivityDetailBinding
import com.example.aplikasigithubuser.detail.follow.FollowsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Detail User")

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val username = intent.getStringExtra("username") ?: ""
        Log.d("user", username.toString())
        viewModel.getDetailUser(username)
        viewModel.resultDetailUser.observe(this){
            when(it){
                is com.example.aplikasigithubuser.utils.Result.Success<*> -> {
                    val user = it.data as ResponseGithubDetail
                    binding.image.load(user.avatar_url){
                        transformations(CircleCropTransformation())
                    }
                    binding.username.text = "@" + user.login
                    binding.nama.text = user.name
                    binding.tvFollowers.text = user.followers.toString()
                    binding.tvFollowing.text = user.following.toString()
                }
                is com.example.aplikasigithubuser.utils.Result.Error -> {
                    Toast.makeText(this, it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is com.example.aplikasigithubuser.utils.Result.Loading -> {
                    binding.progressBar.isVisible = it.isLoading
                }
            }
        }

        viewModel.getDetailUser(username)
        val fragments = mutableListOf<Fragment>(
            FollowsFragment.newInstance(FollowsFragment.FOLLOWERS),
            FollowsFragment.newInstance(FollowsFragment.FOLLOWING)
        )
        val titleFragment = mutableListOf<String>(
            getString(R.string.followers), getString(R.string.following)
        )
        val adapter = DetailAdapter(this, fragments)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.viewpager){ tab , posisi ->
            tab.text = titleFragment[posisi]
        }.attach()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    viewModel.getFollowers(username)
                } else {
                    viewModel.getFollowing(username)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewModel.getFollowers(username)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}