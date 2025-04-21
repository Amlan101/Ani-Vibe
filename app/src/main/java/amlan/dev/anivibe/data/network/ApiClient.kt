package amlan.dev.anivibe.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val animeApi: AnimeApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://your-api-url.com/") // Replace with actual URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApiService::class.java)
    }
}