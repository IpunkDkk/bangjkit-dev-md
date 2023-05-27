package com.example.aplikasigithubuser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplikasigithubuser.data.model.ResponseGithubUser

@Database(entities = [ResponseGithubUser.Item::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
}