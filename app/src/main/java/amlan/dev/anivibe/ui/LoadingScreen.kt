package amlan.dev.anivibe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    prompt: String,
    onResultReady: () -> Unit
) {
    // Animation states
    val infiniteTransition = rememberInfiniteTransition(label = "loading_transition")
    val pulseAnim = infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_animation"
    )

    // Loading messages to cycle through
    val loadingMessages = listOf(
        "Searching for perfect anime matches...",
        "Analyzing your mood...",
        "Finding recommendations for you...",
        "Matching your feelings with stories..."
    )

    var currentMessageIndex by remember { mutableStateOf(0) }
    var messageVisible by remember { mutableStateOf(true) }

    // Cycle through loading messages
    LaunchedEffect(Unit) {
        while (true) {
            delay(2500L)
            messageVisible = false
            delay(300L)
            currentMessageIndex = (currentMessageIndex + 1) % loadingMessages.size
            messageVisible = true
        }
    }

    // Simulate API call
    LaunchedEffect(Unit) {
        delay(5000L) // Simulate loading; replace with real API call later
        onResultReady()
    }

    // Background gradient
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surfaceVariant
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            // Prompt display
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Finding anime based on:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "\"$prompt\"",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Loading indicator
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                            .copy(alpha = pulseAnim.value * 0.3f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 4.dp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Loading message with animation
            AnimatedVisibility(
                visible = messageVisible,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(
                    text = loadingMessages[currentMessageIndex],
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progress bar
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            )
        }
    }
}