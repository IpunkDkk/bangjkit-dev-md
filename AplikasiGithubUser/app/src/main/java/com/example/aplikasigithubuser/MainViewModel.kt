package com.example.aplikasigithubuser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasigithubuser.data.service.ApiClient
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import  com.example.aplikasigithubuser.utils.Result

class MainViewModel: ViewModel() {
    val resultUser = MutableLiveData<Result>()
    fun getUser(){
        viewModelScope.launch{
                flow {
                    val response = ApiClient
                        .githubService
                        .getUsers()
                    Log.d("response", response.toString())
                    emit(response)
                }.onStart {
                    resultUser.value = Result.Loading(true)
                }.onCompletion {
                    resultUser.value = Result.Loading(false)
                }.catch {
                    //error
                    Log.d("error", it.message.toString())
                    it.printStackTrace()

                    resultUser.value = Result.Error(it)

                }.collect{
                    resultUser.value = Result.Success(it)
                }
        }
    }
    fun getUser(user: String){
        viewModelScope.launch{
            flow {
                val response = ApiClient
                    .githubService
                    .getSearchUserGithub(mapOf(
                        "q" to user,
                        "per_page" to 10
                    ))
                Log.d("response", response.toString())
                emit(response)
            }.onStart {
                resultUser.value = Result.Loading(true)
            }.onCompletion {
                resultUser.value = Result.Loading(false)
            }.catch {
                //error
                Log.d("error", it.message.toString())
                it.printStackTrace()

                resultUser.value = Result.Error(it)

            }.collect{
                resultUser.value = Result.Success(it.items)
            }
        }
    }
}