package com.example.demolinkchecker.di

import com.example.demolinkchecker.api.LinkCheckerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder1(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://placeholder.com")
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideLinkCheckerApi(retrofitBuilder: Retrofit.Builder): LinkCheckerApi {
        return retrofitBuilder
            .build()
            .create(LinkCheckerApi::class.java)
    }
}