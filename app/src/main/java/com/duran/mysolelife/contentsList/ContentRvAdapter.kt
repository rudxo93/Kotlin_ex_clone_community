package com.duran.mysolelife.contentsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duran.mysolelife.R

class ContentRvAdapter(val items: ArrayList<ContentModel>) :
    RecyclerView.Adapter<ContentRvAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRvAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRvAdapter.Viewholder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: ContentModel) {
            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)

            contentTitle.text = item.title
        }

    }

}