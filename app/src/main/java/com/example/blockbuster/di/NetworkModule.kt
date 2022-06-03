package com.example.blockbuster.di

import com.example.blockbuster.data.network.APIService
import com.example.blockbuster.data.network.RetrofitClient
import com.example.blockbuster.data.repositories.RemoteInterfacer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()))
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit) : APIService {

        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteInterfacer(api: APIService) : RemoteInterfacer {
        return RetrofitClient(api)
    }

}