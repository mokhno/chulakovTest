package com.example.chulakovtest.network

import com.example.chulakovtest.BuildConfig
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

     val githubApi: GithubApi = getRetrofit().create<GithubApi>(GithubApi::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }
}