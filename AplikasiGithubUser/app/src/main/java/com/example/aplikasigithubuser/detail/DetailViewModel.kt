package com.example.aplikasigithubuser.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasigithubuser.data.service.ApiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    val resultDetailUser = MutableLiveData<com.example.aplikasigithubuser.utils.Result>()
    val resultFollowersUser = MutableLiveData<com.example.aplikasigithubuser.utils.Result>()
    val resultFollowingUser = MutableLiveData<com.example.aplikasigithubuser.utils.Result>()

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
}