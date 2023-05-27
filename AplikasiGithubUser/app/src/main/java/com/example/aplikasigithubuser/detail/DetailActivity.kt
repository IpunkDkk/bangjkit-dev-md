package com.example.aplikasigithubuser.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.local.ModuleDatabase
import com.example.aplikasigithubuser.data.model.ResponseGithubDetail
import com.example.aplikasigithubuser.data.model.ResponseGithubUser
import com.example.aplikasigithubuser.databinding.ActivityDetailBinding
import com.example.aplikasigithubuser.detail.follow.FollowsFragment
import com.example.aplikasigithubuser.favorite.FavoriteActivity
import com.example.aplikasigithubuser.setting.SettingActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModel.Factory(ModuleDatabase(this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Detail User")

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.getParcelableExtra<ResponseGithubUser.Item>("user")
        val username = item?.login ?: ""
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

        viewModel.resultSuccessFavorite.observe(this){
            binding.btnFavorite.changeIconColor(R.color.primary)
        }

        viewModel.resultDeleteFavorite.observe(this){
            binding.btnFavorite.changeIconColor(R.color.onPrimary)
        }

        binding.btnFavorite.setOnClickListener{
            viewModel.setFavorite(item)
        }
        viewModel.findFavorite(item?.id ?: 0){
            binding.btnFavorite.changeIconColor(R.color.primary)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.setting -> {
                Intent(this, SettingActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun FloatingActionButton.changeIconColor(@ColorRes color: Int) {
        imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.favorite)
        item?.isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }

}