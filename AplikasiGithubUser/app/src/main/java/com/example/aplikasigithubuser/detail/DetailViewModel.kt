package com.example.aplikasigithubuser.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.aplikasigithubuser.data.local.ModuleDatabase
import com.example.aplikasigithubuser.data.model.ResponseGithubUser
import com.example.aplikasigithubuser.data.service.ApiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(private val db: ModuleDatabase): ViewModel() {
    val resultDetailUser = MutableLiveData<com.example.aplikasigithubuser.utils.Result>()
    val resultFollowersUser = MutableLiveData<com.example.aplikasigithubuser.utils.Result>()
    val resultFollowingUser = MutableLiveData<com.example.aplikasigithubuser.utils.Result>()
    val resultSuccessFavorite = MutableLiveData<Boolean>()
    val resultDeleteFavorite = MutableLiveData<Boolean>()

    private var isFavorite = false

    fun setFavorite(item: ResponseGithubUser.Item?) {
        viewModelScope.launch {
            item?.let {
                if (isFavorite) {
                    db.userDao.delete(item)
                    resultDeleteFavorite.value = true
                } else {
                    db.userDao.insert(item)
                    resultSuccessFavorite.value = true
                }
            }
            isFavorite = !isFavorite
        }
    }


    fun findFavorite(id:Int, listenFavorite:() -> Unit){
        viewModelScope.launch {
            val user = db.userDao.findById(id)
            Log.d("id_data", id.toString())
            if (user != null){
                listenFavorite()
                Log.d("result", user.toString())
                isFavorite = true
            }
        }
    }

    fun getDetailUser(username:String){
        viewModelScope.launch {
            flow {
                val response= ApiClient
                    .githubService
                    .getDetailUser(username)
                emit(response)
            }.onStart {
                resultDetailUser.value = com.example.aplikasigithubuser.utils.Result.Loading(true)
            }.onCompletion {
                resultDetailUser.value = com.example.aplikasigithubuser.utils.Result.Loading(false)
            }.catch {
                it.printStackTrace()
                resultDetailUser.value = com.example.aplikasigithubuser.utils.Result.Error(it)
            }.collect{
                resultDetailUser.value = com.example.aplikasigithubuser.utils.Result.Success(it)
            }
        }
    }

    fun getFollowers(username: String){
        viewModelScope.launch {
            flow {
                val response= ApiClient
                    .githubService
                    .getFollowersUser(username)
                emit(response)
            }.onStart {
                resultFollowersUser.value = com.example.aplikasigithubuser.utils.Result.Loading(true)
            }.onCompletion {
                resultFollowersUser.value = com.example.aplikasigithubuser.utils.Result.Loading(false)
            }.catch {
                it.printStackTrace()
                resultFollowersUser.value = com.example.aplikasigithubuser.utils.Result.Error(it)
            }.collect{
                resultFollowersUser.value = com.example.aplikasigithubuser.utils.Result.Success(it)
            }
        }
    }

    fun getFollowing(username: String){
        viewModelScope.launch {
            flow {
                val response= ApiClient
                    .githubService
                    .getFollowingUser(username)
                emit(response)
            }.onStart {
                resultFollowingUser.value = com.example.aplikasigithubuser.utils.Result.Loading(true)
            }.onCompletion {
                resultFollowingUser.value = com.example.aplikasigithubuser.utils.Result.Loading(false)
            }.catch {
                it.printStackTrace()
                resultFollowingUser.value = com.example.aplikasigithubuser.utils.Result.Error(it)
            }.collect{
                resultFollowingUser.value = com.example.aplikasigithubuser.utils.Result.Success(it)
            }
        }
    }
    class Factory(private val db:ModuleDatabase): ViewModelProvider.NewInstanceFactory(){
        override fun <T: ViewModel> create(modelClass: Class<T>): T = DetailViewModel(db) as T
    }
}