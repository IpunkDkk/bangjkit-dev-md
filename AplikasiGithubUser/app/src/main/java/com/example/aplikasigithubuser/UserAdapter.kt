package com.example.aplikasigithubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.aplikasigithubuser.data.model.ResponseGithubUser
import com.example.aplikasigithubuser.databinding.ItemUserBinding

class UserAdapter (
    private val data:MutableList<ResponseGithubUser.Item> = mutableListOf(),
    private val listener:(ResponseGithubUser.Item) -> Unit
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setData(data: MutableList<ResponseGithubUser.Item>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class UserViewHolder(private val v: ItemUserBinding) : RecyclerView.ViewHolder(v.root){
        fun bind(item: ResponseGithubUser.Item){
            v.image.load(item.avatarUrl){
                transformations(CircleCropTransformation())
            }
            v.username.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            listener(item)
        }
    }

    override fun getItemCount(): Int = data.size
}