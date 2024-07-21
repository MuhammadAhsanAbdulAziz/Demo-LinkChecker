package com.example.demolinkchecker.utils

import org.json.JSONObject

sealed class NetworkResult<T>(val data:T? = null, val error: T? = null)
{
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T? = null) : NetworkResult<T>(data = data)
    class Error<T>(errorMessage :T? = null) : NetworkResult<T>(error = errorMessage)
}