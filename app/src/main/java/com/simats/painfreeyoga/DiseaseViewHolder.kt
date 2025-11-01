package com.simats.painfreeyoga

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val diseaseNameText: TextView = itemView.findViewById(R.id.diseaseNameText)
    fun bind(name: String) {
        diseaseNameText.text = name
    }
}
