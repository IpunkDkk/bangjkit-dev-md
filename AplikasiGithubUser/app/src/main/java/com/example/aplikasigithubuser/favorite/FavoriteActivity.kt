package com.example.aplikasigithubuser.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.UserAdapter
import com.example.aplikasigithubuser.data.local.ModuleDatabase
import com.example.aplikasigithubuser.databinding.ActivityFavoriteBinding
import com.example.aplikasigithubuser.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModel.Factory(ModuleDatabase(this))
    }
    private val adapter by lazy {
        UserAdapter {
            Intent(this, DetailActivity::class.java).apply {
                putExtra("user", it)
                startActivity(this)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter

        viewModel.getUserFavorite().observe(this){
            adapter.setData(it)
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}