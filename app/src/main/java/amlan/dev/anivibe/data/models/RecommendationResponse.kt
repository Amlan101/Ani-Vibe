package amlan.dev.anivibe.data.models

data class RecommendationResponse(
    val results: List<Anime>
)

data class Anime(
    val title: String,
    val explanation: String,
    val genres: List<String> = emptyList(),
    val episodes: Float? = null,
    val rating: Float? = null,
    val ranked: Float? = null,
    val aired: String? = null,
    val synopsis: String? = null,
    val imgUrl: String? = null
)