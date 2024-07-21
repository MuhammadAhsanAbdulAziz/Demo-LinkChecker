package com.example.demolinkchecker.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface LinkCheckerApi {

    @GET
    suspend fun checkLink(@Url url: String) : Response<Void>

}