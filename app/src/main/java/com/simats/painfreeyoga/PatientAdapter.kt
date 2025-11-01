package com.simats.painfreeyoga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simats.painfreeyoga.api.PatientData

class PatientAdapter(
    private val patientList: List<PatientData>,
    private val onItemClick: (PatientData) -> Unit
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val age: TextView = itemView.findViewById(R.id.ageTextView)
        val problem: TextView = itemView.findViewById(R.id.problemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patientList[position]
        holder.name.text = "Name: ${patient.patientname}"
        holder.age.text = "Age: ${patient.age}"
        holder.problem.text = "Problem: ${patient.selectdisease}"
        holder.itemView.setOnClickListener { onItemClick(patient) }
    }

    override fun getItemCount(): Int = patientList.size
}
