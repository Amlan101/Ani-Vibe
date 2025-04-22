package amlan.dev.anivibe.viewmodel

import amlan.dev.anivibe.data.models.Anime
import amlan.dev.anivibe.data.models.PromptRequest
import amlan.dev.anivibe.data.network.ApiClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecommendationViewModel: ViewModel() {

    private val _recommendations = MutableStateFlow<List<Anime>>(emptyList())
    val recommendations: StateFlow<List<Anime>> = _recommendations

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Added to store the current prompt
    private val _currentPrompt = MutableStateFlow("")
    val currentPrompt: StateFlow<String> = _currentPrompt.asStateFlow()

    // Function to set the current prompt
    fun setCurrentPrompt(prompt: String) {
        _currentPrompt.value = prompt
    }

    private val _selectedAnime = MutableStateFlow<Anime?>(null)
    val selectedAnime: StateFlow<Anime?> = _selectedAnime

    fun selectAnime(anime: Anime) {
        _selectedAnime.value = anime
    }

    fun fetchRecommendations(prompt: String, onDone: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.animeApi.getRecommendations(PromptRequest(prompt))
                _recommendations.value = response.results
                _error.value = null
                onDone()
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }
}


