package com.duran.mysolelife.contentsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duran.mysolelife.R

class ContentRvAdapter(val context: Context, val items: ArrayList<ContentModel>) : RecyclerView.Adapter<ContentRvAdapter.Viewholder>() {

    // item click
    interface  ItemClick {
        fun onClick(view: View, position: Int)
    }
    // item click
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRvAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRvAdapter.Viewholder, position: Int) {
        holder.bindItem(items[position])
        // item click
        if(itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: ContentModel) {
            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)

            contentTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)


        }

    }

}