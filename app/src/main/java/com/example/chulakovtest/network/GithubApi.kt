package com.example.chulakovtest.network

import com.example.chulakovtest.Model.Profile
import com.example.chulakovtest.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("users")
     fun getUsers(@Query("since") since: Int): Call<List<User>>


    @GET("users/{login}")
     fun getProfile(@Path("login") login: String): Call<Profile>
}