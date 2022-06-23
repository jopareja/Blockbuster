package com.example.blockbuster.di

import com.example.blockbuster.data.network.APIService
import com.example.blockbuster.data.network.RetrofitClient
import com.example.blockbuster.data.repositories.RemoteProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
//In charge of providing libraries and/or classes that implement interfaces
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Everytime our app needs retrofit, we can provide it with below
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit) : APIService {

        return retrofit.create(APIService::class.java)
    }

    // If I need to return another client to the Repo, I have to change the client here.
    @Singleton
    @Provides
    fun provideRemoteProvider(api: APIService) : RemoteProvider {
        return RetrofitClient(api)
    }

}