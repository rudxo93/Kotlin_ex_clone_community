package com.duran.mysolelife.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duran.mysolelife.R
import com.duran.mysolelife.utils.FBAuth
import com.duran.mysolelife.utils.FBRef

class ContentRvAdapter(val context: Context, val items: ArrayList<ContentModel>, val keyList : ArrayList<String>) : RecyclerView.Adapter<ContentRvAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRvAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRvAdapter.Viewholder, position: Int) {
        holder.bindItem(items[position], keyList[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: ContentModel, key : String) {

            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            bookmarkArea.setOnClickListener {
                Log.d("ContentRvAdapter", FBAuth.getUid())
                Toast.makeText(context, key, Toast.LENGTH_SHORT).show()

                FBRef.bookmarkRef.child(FBAuth.getUid()).child(key).setValue("good")
            }

            contentTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)


        }

    }

}