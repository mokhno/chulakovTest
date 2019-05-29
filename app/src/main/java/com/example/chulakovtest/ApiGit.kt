package com.example.chulakovtest

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiGit {
    private val client: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private var api: ApiUtils? = null
    val apiService: ApiUtils
        get() {
            if (api == null) {
                api = getRetrofit().create<ApiUtils>(ApiUtils::class.java!!)
            }
            return api!!

        }

    //    public static OkHttpClient getClient() {
    //        client = new OkHttpClient();
    //        return client;
    //    }

    private fun getRetrofit(): Retrofit {
        if (gson == null) {
            gson = Gson()
        }
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                //                    .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson!!))
                .build()
        }
        return retrofit!!

    }
}