package amlan.dev.anivibe.data.models

data class RecommendationResponse(
    val results: List<Anime>
)

data class Anime(
    val title: String,
    val imageUrl: String,
    val explanation: String
)