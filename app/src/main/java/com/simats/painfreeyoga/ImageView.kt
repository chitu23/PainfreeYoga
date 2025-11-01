package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.painfreeyoga.api.DiseaseResponse
import com.simats.painfreeyoga.api.RetrofitClient
import com.example.painfreeyoga.api.YogaPose
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageView : AppCompatActivity() {
    private lateinit var yogaRecyclerView: RecyclerView
    private lateinit var completedButton: Button

    private var allYogaPoses = listOf<YogaPose>()
    private var isShowingDiseases = true // Flag to toggle view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        yogaRecyclerView = findViewById(R.id.yogaRecyclerView)
        completedButton = findViewById(R.id.button2)

        yogaRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch API data
        RetrofitClient.instance.getDiseaseData().enqueue(object : Callback<DiseaseResponse> {
            override fun onResponse(call: Call<DiseaseResponse>, response: Response<DiseaseResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    allYogaPoses = response.body()?.data ?: emptyList()

                    // Get unique diseases
                    val uniqueDiseases = allYogaPoses.map { it.diseasename }.distinct()
                    showDiseaseList(uniqueDiseases)
                } else {
                    Toast.makeText(this@ImageView, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DiseaseResponse>, t: Throwable) {
                Toast.makeText(this@ImageView, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        completedButton.setOnClickListener {
            startActivity(Intent(this, SettingPage::class.java))
        }
    }

    private fun showDiseaseList(diseaseList: List<String>) {
        isShowingDiseases = true
        yogaRecyclerView.adapter = object : RecyclerView.Adapter<DiseaseViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
                val view = layoutInflater.inflate(R.layout.item_disease_name, parent, false)
                return DiseaseViewHolder(view)
            }

            override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
                val diseaseName = diseaseList[position]
                holder.bind(diseaseName)
                holder.itemView.setOnClickListener {
                    val filtered = allYogaPoses.filter { it.diseasename == diseaseName }
                    showYogaList(filtered)
                }
            }

            override fun getItemCount(): Int = diseaseList.size
        }
    }

    private fun showYogaList(yogaList: List<YogaPose>) {
        isShowingDiseases = false
        yogaRecyclerView.layoutManager = GridLayoutManager(this, 2)
        yogaRecyclerView.adapter = ImageViewAdapter(yogaList)
    }

    // Optional: handle back button to return to disease list
    override fun onBackPressed() {
        if (!isShowingDiseases) {
            val uniqueDiseases = allYogaPoses.map { it.diseasename }.distinct()
            yogaRecyclerView.layoutManager = LinearLayoutManager(this)
            showDiseaseList(uniqueDiseases)
        } else {
            super.onBackPressed()
        }
    }
}
