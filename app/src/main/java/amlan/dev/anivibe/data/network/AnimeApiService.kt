package amlan.dev.anivibe.data.network

import amlan.dev.anivibe.data.models.PromptRequest
import amlan.dev.anivibe.data.models.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AnimeApiService {
    @POST("recommendations")
    suspend fun getRecommendations(@Body prompt: PromptRequest): RecommendationResponse
}