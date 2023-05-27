package com.example.aplikasigithubuser.data.local

import android.content.Context
import androidx.room.Room

class ModuleDatabase(private val context: Context) {
    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "usergithub.db")
        .allowMainThreadQueries()
        .build()
    val userDao = db.userDao()
}