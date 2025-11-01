package com.simats.painfreeyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.simats.painfreeyoga.api.Admin
import com.simats.painfreeyoga.api.ApiResponse
import com.simats.painfreeyoga.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminProfileFragment : Fragment() {

    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var gender: EditText
    private lateinit var number: EditText
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_profile, container, false)

        name = view.findViewById(R.id.editTextDoctorName)
        age = view.findViewById(R.id.editTextAge)
        gender = view.findViewById(R.id.editTextGender)
        number = view.findViewById(R.id.editTextNumber)
        button = view.findViewById(R.id.button)

        fetchAdminDetails()

        button.setOnClickListener {
            val adminname = name.text.toString()
            val adminage = age.text.toString()
            val admingender = gender.text.toString()
            val adminnumber = number.text.toString()

            if (adminname.isNotEmpty() && adminage.isNotEmpty() && admingender.isNotEmpty() && adminnumber.isNotEmpty()) {
                insertAdmin(adminname, adminage, admingender, adminnumber)
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun fetchAdminDetails() {

        val call = RetrofitClient.instance.getAdminDetails()

        call.enqueue(object : Callback<Admin> {
            override fun onResponse(
                call: Call<Admin?>,
                response: Response<Admin?>
            ) {
               if (response.isSuccessful){
                   if (response.body() !=null){
                       val data = response.body()
                       name.setText(data?.adminname)
                       age.setText(data?.age)
                       gender.setText(data?.gender)
                       number.setText(data?.mobilenumber)
                   }
               } else{
                   val error = response.errorBody()?.string()
                   Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show()

               }
            }

            override fun onFailure(
                call: Call<Admin?>,
                t: Throwable
            ) {
                Toast.makeText(requireContext(),t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun insertAdmin(name: String, age: String, gender: String, number: String) {
        val call = RetrofitClient.instance.insertAdmin(name, age, gender, number)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Toast.makeText(
                        requireContext(),
                        result?.message ?: "No response",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Failed: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
