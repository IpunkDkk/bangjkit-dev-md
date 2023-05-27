package com.example.aplikasigithubuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.aplikasigithubuser.data.model.ResponseGithubUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ResponseGithubUser.Item)

    @Query("SELECT * FROM User")
    fun loadAll(): LiveData<MutableList<ResponseGithubUser.Item>>

    @Query("SELECT * FROM User WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): ResponseGithubUser.Item

    @Delete
    fun delete(user: ResponseGithubUser.Item)
}