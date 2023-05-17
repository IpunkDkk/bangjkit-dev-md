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
		@field:SerializedName("gists_url")
		val gistsUrl: String,

		@field:SerializedName("repos_url")
		val reposUrl: String,

		@field:SerializedName("following_url")
		val followingUrl: String,

		@field:SerializedName("starred_url")
		val starredUrl: String,

		@field:SerializedName("login")
		val login: String,

		@field:SerializedName("followers_url")
		val followersUrl: String,

		@field:SerializedName("type")
		val type: String,

		@field:SerializedName("url")
		val url: String,

		@field:SerializedName("subscriptions_url")
		val subscriptionsUrl: String,

		@field:SerializedName("received_events_url")
		val receivedEventsUrl: String,

		@field:SerializedName("avatar_url")
		val avatarUrl: String,

		@field:SerializedName("events_url")
		val eventsUrl: String,

		@field:SerializedName("html_url")
		val htmlUrl: String,

		@field:SerializedName("site_admin")
		val siteAdmin: Boolean,

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("gravatar_id")
		val gravatarId: String,

		@field:SerializedName("node_id")
		val nodeId: String,

		@field:SerializedName("organizations_url")
		val organizationsUrl: String
//		@ColumnInfo(name = "avatar_url")
//		val avatar_url: String,
//		@PrimaryKey
//		val id: Int,
//		@ColumnInfo(name = "login")
//		val login: String,
	) : Parcelable
}
