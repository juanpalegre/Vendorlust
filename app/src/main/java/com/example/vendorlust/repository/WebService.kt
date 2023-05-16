package com.example.vendorlust.repository

import com.example.vendorlust.application.AppConstants
import com.example.vendorlust.data.model.Vendor
import com.example.vendorlust.data.model.VendorList
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    @GET("vendorlust.json")
    suspend fun getVendorList(): List<Vendor>

}

object RetrofitClient {

    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}