package com.example.aplikasigithubuser.data.model


data class ResponseGithubDetail(
	val avatar_url: String,
	val bio: Any,
	val blog: String,
	val company: String,
	val created_at: String,
	val email: Any,
	val events_url: String,
	val followers: Int,
	val following: Int,
	val id: Int,
	val location: String,
	val login: String,
	val name: String,
	val node_id: String,
	val organizations_url: String,
	val public_gists: Int,
	val public_repos: Int,
	val received_events_url: String,
	val site_admin: Boolean,
	val subscriptions_url: String,
	val twitter_username: Any,
	val type: String,
	val updated_at: String,
	val url: String
)
