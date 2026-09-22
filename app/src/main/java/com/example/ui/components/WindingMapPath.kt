package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.LevelProgressEntity
import com.example.data.model.LevelData
import com.example.ui.theme.GameCyan
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GameLocked
import com.example.ui.theme.GamePink
import com.example.ui.theme.GamePurple
import com.example.ui.theme.GamePurpleDark
import kotlin.math.sin

@Composable
fun WindingLevelMap(
    levels: List<LevelData>,
    progressMap: Map<Int, LevelProgressEntity>,
    highestUnlocked: Int,
    onLevelClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    // Auto-scroll to active level on initial display
    LaunchedEffect(highestUnlocked) {
        val targetIndex = (highestUnlocked - 1).coerceIn(0, levels.size - 1)
        listState.animateScrollToItem(targetIndex)
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val totalWidth = maxWidth
        val nodeSpacing = 110.dp

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = 24.dp, bottom = 96.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("level_map_list")
        ) {
            items(levels, key = { it.id }) { level ->
                val progress = progressMap[level.id]
                val isUnlocked = level.id <= highestUnlocked || progress?.unlocked == true
                val isCompleted = progress?.completed == true
                val isCurrent = level.id == highestUnlocked && !isCompleted
                val isMilestone = level.id % 10 == 0

                // Winding X offset based on sine wave pattern
                val waveOffset = (sin(level.id * 0.9) * 0.32).toFloat() // -0.32 to +0.32
                val horizontalAlignment = Alignment.Center

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(nodeSpacing),
                    contentAlignment = Alignment.Center
                ) {
                    // Node with horizontal displacement
                    val xOffsetDp = (totalWidth.value * waveOffset).dp

                    LevelNode(
                        level = level,
                        progress = progress,
                        isUnlocked = isUnlocked,
                        isCompleted = isCompleted,
                        isCurrent = isCurrent,
                        isMilestone = isMilestone,
                        onClick = {
                            if (isUnlocked) {
                                onLevelClick(level.id)
                            }
                        },
                        modifier = Modifier.offset(x = xOffsetDp)
                    )
                }
            }
        }
    }
}

@Composable
fun LevelNode(
    level: LevelData,
    progress: LevelProgressEntity?,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    isCurrent: Boolean,
    isMilestone: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "current_pulse"
    )

    val nodeSize = when {
        isMilestone -> 76.dp
        isCurrent -> 70.dp
        else -> 62.dp
    }

    val baseScale = if (isCurrent) pulseScale else 1f

    val nodeBrush = when {
        isCompleted -> Brush.linearGradient(
            listOf(GameGreen, Color(0xFF34D399))
        )
        isCurrent -> Brush.linearGradient(
            listOf(GamePink, Color(0xFFF59E0B))
        )
        isMilestone && isUnlocked -> Brush.linearGradient(
            listOf(GameGold, Color(0xFFFBBF24))
        )
        isUnlocked -> Brush.linearGradient(
            listOf(GamePurple, Color(0xFFA855F7))
        )
        else -> Brush.linearGradient(
            listOf(GameLocked, Color(0xFF94A3B8))
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .scale(baseScale)
                .shadow(
                    elevation = if (isUnlocked) 8.dp else 2.dp,
                    shape = CircleShape,
                    spotColor = if (isCurrent) GamePink else GamePurple
                )
                .size(nodeSize)
                .clip(CircleShape)
                .background(nodeBrush)
                .border(
                    width = if (isMilestone) 3.5.dp else 2.5.dp,
                    color = if (isCurrent) Color.White else if (isMilestone) GameGold else Color.White.copy(alpha = 0.8f),
                    shape = CircleShape
                )
                .clickable(enabled = isUnlocked, onClick = onClick)
                .testTag("level_node_${level.id}")
        ) {
            when {
                isCompleted -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Completed",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "${level.id}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                isCurrent -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                        Text(
                            text = "${level.id}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }
                isUnlocked -> {
                    Text(
                        text = "${level.id}",
                        fontSize = if (isMilestone) 22.sp else 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        // Stars for completed levels or Milestone Badge
        if (isCompleted) {
            val stars = progress?.stars ?: 1
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < stars) GameGold else Color(0xFFCBD5E1),
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        } else if (isMilestone) {
            Surface(
                color = GameGold.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 3.dp)
            ) {
                Text(
                    text = "BOSS",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    color = GameGold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}
