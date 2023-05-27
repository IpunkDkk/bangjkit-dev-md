package com.example.aplikasigithubuser.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseGithubUser(
	val incomplete_results: Boolean,
	val items: MutableList<Item>,
	val total_count: Int
) {
	@Parcelize
	@Entity(tableName = "user")
	data class Item(
		@ColumnInfo(name = "avatar_url")
		val avatar_url: String,
		@PrimaryKey
		val id: Int,
		@ColumnInfo(name = "login")
		val login: String,
	) : Parcelable
}

