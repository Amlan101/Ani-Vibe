package amlan.dev.anivibe.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val animeApi: AnimeApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://13.126.232.83:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApiService::class.java)
    }
}