package com.example.aplikasigithubuser.data.service

import com.example.aplikasigithubuser.BuildConfig
import com.example.aplikasigithubuser.data.model.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GithubService {
    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUsers(
        @Header("Authorization")
        authorization: String = BuildConfig.TOKEN
    ): MutableList<ResponseGithubUser.Item>

    @JvmSuppressWildcards
    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username : String,
        @Header("Authorization")
        authorization: String = BuildConfig.TOKEN
    ): ResponseGithubDetail

    @JvmSuppressWildcards
    @GET("/users/{username}/followers")
    suspend fun getFollowersUser(
        @Path("username") username : String,
        @Header("Authorization")
        authorization: String = BuildConfig.TOKEN
    ): MutableList<ResponseGithubUser.Item>

    @JvmSuppressWildcards
    @GET("/users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username : String,
        @Header("Authorization")
        authorization: String = BuildConfig.TOKEN
    ): MutableList<ResponseGithubUser.Item>

    @JvmSuppressWildcards
    @GET("search/users")
    suspend fun getSearchUserGithub(
        @QueryMap params: Map<String, Any>,
        @Header("Authorization")
        authorization: String = BuildConfig.TOKEN
    ): ResponseGithubUser


}