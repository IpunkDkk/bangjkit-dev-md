package com.example.jeniskucing

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListCatAdapter(private val listCat: ArrayList<Cat>) : RecyclerView.Adapter<ListCatAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_cat, parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, caracter) = listCat[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvDescription.text = caracter
        holder.itemView.setOnClickListener{
            val detailCat = Intent(holder.itemView.context, DetailCatActivity::class.java)
            detailCat.putExtra(DetailCatActivity.DETAIL_CAT, listCat[holder.adapterPosition])
            holder.itemView.context.startActivity(detailCat)
        }
    }

    override fun getItemCount(): Int = listCat.size
}