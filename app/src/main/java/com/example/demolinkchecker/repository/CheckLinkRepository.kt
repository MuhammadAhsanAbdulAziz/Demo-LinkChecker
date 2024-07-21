package com.example.demolinkchecker.repository

import android.util.Log
import com.example.demolinkchecker.api.LinkCheckerApi
import javax.inject.Inject

class CheckLinkRepository @Inject constructor(private val linkCheckerApi: LinkCheckerApi) {

    suspend fun checkLink(url:String) {

        try {
            val response = linkCheckerApi.checkLink(url)
            if(response.isSuccessful){
                Log.d("Link Status", "${response.code()}")
            }
            else{
                Log.d("Link Status", "Not Working")
            }
        } catch (e : Exception){
            Log.d("Link Status", "Not Working")
        }

    }
}