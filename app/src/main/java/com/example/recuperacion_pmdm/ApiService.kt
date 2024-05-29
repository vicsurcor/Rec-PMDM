package com.example.recuperacion_pmdm

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class User(val username: String, val email: String, val password: String)
data class LoginRequest(val username: String, val password: String)
data class ChatMessage(val username: String, val location: String, val message: String)
data class ApiResponse(val message: String)

interface ApiService {
    @POST("register")
    fun register(@Body user: User): Call<ApiResponse>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<ApiResponse>

    @POST("chat")
    fun sendMessage(@Body chatMessage: ChatMessage): Call<ApiResponse>

    @GET("messages")
    fun getMessages(): Call<List<ChatMessage>>
}