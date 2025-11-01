package com.simats.painfreeyoga.api


data class PatientListResponse(
    val status: Boolean,
    val message: String,
    val data : List<PatientData>
)

data class PatientData(
    val id: String,
    val patientname: String,
    val age: String,
    val gender: String,
    val mobilenumber: String,
    val email: String,
    val selectdisease: String
)
