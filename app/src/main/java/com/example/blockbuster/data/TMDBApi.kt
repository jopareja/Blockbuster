package com.example.blockbuster.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    //suspend so the method getPopularMovies gets called in a coroutine
    @GET("movie/popular?api_key=268eca7ce3ecdeb900e477ba297a3587")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MoviesFirstResponse>
}

// My Api will use Retrofit's services
object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}

//Domain Service - Capa Intermedia: #Servicio que invoca al retrofit.
//Domain Service - Capa Intermedia: #Mapea a data class que va a utilizar el ViewModel
//Utilizo esto, en caso de que la respuesta de la Api se modifique, y no me rompa el codigo
//
//class MovieService {

    //suspend fun getPopularMovies() : List<Movie2>{
        //return Api.retrofitService.getPopularMovies(0).body()?.results?.map { it.toMovie2() } ?: emptyList()
    //}
//}
// La que va a traer el ViewModel
//data class Movie2(val id: Long,
                 //val title: String,
                 //val overview: String)


//fun Movie.toMovie2() = Movie2(id,title,overview)