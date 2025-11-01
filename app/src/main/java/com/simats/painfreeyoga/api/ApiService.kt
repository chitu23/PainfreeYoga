package com.simats.painfreeyoga.api

import com.example.painfreeyoga.api.DiseaseResponse
import com.simats.painfreeyoga.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // User signup
    @FormUrlEncoded
    @POST("signup.php")
    fun signUp(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<SignupResponse>

    // User login
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    // Get questions
    @GET("getquestion.php")
    fun getQuestion(): Call<QuestionResponse>

    // Submit patient details
    @FormUrlEncoded
    @POST("signup.php")
    fun PatientDetails(
        @Field("name") name: String,
        @Field("age") age: String,
        @Field("gender") gender: String,
        @Field("number") number: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("disease") disease: String
    ): Call<ApiResponse>

    // Get disease data
    @GET("disease.php")
    fun getDiseaseData(): Call<DiseaseResponse>

    // Admin login
    @FormUrlEncoded
    @POST("adminlogin.php")
    fun loginAdmin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<AdminLoginResponse>

    // Insert admin details
    @FormUrlEncoded
    @POST("admindetails.php")
    fun insertAdmin(
        @Field("adminname") adminname: String,
        @Field("age") age: String,
        @Field("gender") gender: String,
        @Field("mobilenumber") mobilenumber: String
    ): Call<ApiResponse>

    // Get admin details
    @GET("admindetails.php")
    fun getAdminDetails(): Call<Admin>

    // Submit contraindications
    @FormUrlEncoded
    @POST("answer.php")
    fun submitAnswer(
        @Field("email") email: String,
        @Field("answer") contraindications: String,
    ): Call<AnswerResponse>

    // ✅ Get patients
    @GET("patientdetails.php")
    fun getPatients(): Call<PatientListResponse>

}
