package com.example.widas.network

import com.example.widas.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/api")
    fun getData(@Query("results") pageItems: Int): Call<DataResponse?>?

}