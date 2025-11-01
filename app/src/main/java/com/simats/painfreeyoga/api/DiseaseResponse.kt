package com.example.painfreeyoga.api

data class DiseaseResponse(
    val status: String,
    val data: List<YogaPose>
)

data class YogaPose(
    val id: Int,
    val diseasename: String,
    val yogaimage: String,
    val yoganame: String,
    val yoga_video :String
)
