package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simats.painfreeyoga.api.PatientListResponse
import com.simats.painfreeyoga.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminHomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PatientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fetchPatientData()

        return view
    }

    private fun fetchPatientData() {
        RetrofitClient.instance.getPatients().enqueue(object : Callback<PatientListResponse> {
            override fun onResponse(call: Call<PatientListResponse>, response: Response<PatientListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val patients = response.body()!!.data
                    adapter = PatientAdapter(patients) { selectedPatient ->
                        val intent = Intent(requireContext(), PatientActivity::class.java).apply {
                            putExtra("name", selectedPatient.patientname)
                            putExtra("age", selectedPatient.age)
                            putExtra("problem", selectedPatient.selectdisease)
                        }
                        startActivity(intent)
                    }
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "No patient data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PatientListResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
