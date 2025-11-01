package com.simats.painfreeyoga.api

data class AnswerRequest(
    val email: String,
    val contraindications: String,
    val cobrapose: String
)

data class AnswerResponse(
    val status: Boolean,
    val message: String
)
