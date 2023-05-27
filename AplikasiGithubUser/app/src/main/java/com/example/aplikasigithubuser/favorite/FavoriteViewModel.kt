package com.example.aplikasigithubuser.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.data.local.ModuleDatabase

class FavoriteViewModel(private val dbModule: ModuleDatabase): ViewModel(){

    fun getUserFavorite() = dbModule.userDao.loadAll()
    class Factory(private val db:ModuleDatabase): ViewModelProvider.NewInstanceFactory(){
        override fun <T: ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}