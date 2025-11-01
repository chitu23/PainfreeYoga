package com.simats.painfreeyoga

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.painfreeyoga.api.YogaPose
import com.simats.painfreeyoga.api.RetrofitClient.BASE_URL


class ImageViewAdapter(private val list: List<YogaPose>) :
    RecyclerView.Adapter<ImageViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val yogaImage: ImageView = itemView.findViewById(R.id.imagePose)
        val yogaTitle: TextView = itemView.findViewById(R.id.textPoseName)

        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("VIDEO_URL", list[adapterPosition].yoga_video)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_image_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.yogaTitle.text = item.yoganame
        Glide.with(holder.itemView.context)
            .load(BASE_URL+item.yogaimage)
            .into(holder.yogaImage)
    }

    override fun getItemCount(): Int = list.size
}
