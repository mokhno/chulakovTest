package com.example.chulakovtest

import com.example.chulakovtest.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUtils {
    @GET("users")
    abstract fun getUsers(@Query("since") since:Int): Call<List<User>>
}