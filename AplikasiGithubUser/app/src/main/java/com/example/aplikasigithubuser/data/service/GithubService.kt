package com.example.aplikasigithubuser.data.service

import com.example.aplikasigithubuser.BuildConfig
import com.example.aplikasigithubuser.data.model.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GithubService {
    @JvmSuppressWildcards
    @GET("users")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    suspend fun getUsers(
    ): MutableList<ResponseGithubUser.Item>

    @JvmSuppressWildcards
    @GET("users/{username}")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    suspend fun getDetailUser(
        @Path("username") username : String,
    ): ResponseGithubDetail

    @JvmSuppressWildcards
    @GET("/users/{username}/followers")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    suspend fun getFollowersUser(
        @Path("username") username : String,
    ): MutableList<ResponseGithubUser.Item>

    @JvmSuppressWildcards
    @GET("/users/{username}/following")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    suspend fun getFollowingUser(
        @Path("username") username : String,
    ): MutableList<ResponseGithubUser.Item>

    @JvmSuppressWildcards
    @GET("search/users")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    suspend fun getSearchUserGithub(
        @QueryMap params: Map<String, Any>,
    ): ResponseGithubUser


}