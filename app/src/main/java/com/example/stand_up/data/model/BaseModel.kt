package com.example.stand_up.data.model

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.example.stand_up.util.Constants
import com.example.stand_up.networks.JojoCustomerApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel protected constructor(context: Context){
    protected var mTheApi : JojoCustomerApi
    protected var mStringApi : JojoCustomerApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180,TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .build()
        val gSon = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gSon))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        val retrofitString = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        mTheApi = retrofit.create(JojoCustomerApi::class.java)
        mStringApi = retrofitString.create(JojoCustomerApi::class.java)
    }
}