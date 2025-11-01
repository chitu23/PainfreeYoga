package com.simats.painfreeyoga

data class AdminLoginResponse(
    val status: Boolean,
    val message: String,
    val data: AdminData? = null
)

data class AdminData(
    val adminname: String,
    val email: String,
    val adminid: String
)
