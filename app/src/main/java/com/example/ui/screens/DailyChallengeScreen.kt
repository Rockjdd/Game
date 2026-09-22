package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.components.NumberKeypad
import com.example.ui.components.PuzzleVisualizer
import com.example.ui.theme.GameCanvas
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GamePink
import com.example.ui.theme.GamePurple
import com.example.ui.theme.GamePurpleBg
import com.example.ui.theme.GamePurpleDark
import com.example.ui.viewmodel.DailyChallengeViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyChallengeScreen(
    viewModel: DailyChallengeViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dailyProgress by viewModel.dailyProgress.collectAsState()
    val userStats by viewModel.userStats.collectAsState()
    val currentIndex by viewModel.currentPuzzleIndex.collectAsState()
    val currentInput by viewModel.currentInput.collectAsState()
    val isCompletedDialogVisible by viewModel.isCompletedDialogVisible.collectAsState()
    val showErrorShake by viewModel.showErrorShake.collectAsState()

    val currentDailyPuzzle = viewModel.currentDailyPuzzle

    val shakeOffset = remember { Animatable(0f) }
    LaunchedEffect(showErrorShake) {
        if (showErrorShake) {
            repeat(3) {
                shakeOffset.animateTo(20f, tween(50))
                shakeOffset.animateTo(-20f, tween(50))
            }
            shakeOffset.animateTo(0f, tween(50))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Daily Challenge",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "${viewModel.todayKey} • ${dailyProgress.completedCount}/10 Done",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GamePink
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("daily_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF0F172A)
                        )
                    }
                },
                actions = {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = GamePurpleBg,
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🪙", fontSize = 13.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${userStats.coins}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = GamePurpleDark
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = GameCanvas,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .offset { IntOffset(shakeOffset.value.roundToInt(), 0) }
            ) {
                // Selector Row (Puzzles 1 to 10)
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(viewModel.dailyPuzzles) { index, item ->
                        val isSelected = index == currentIndex
                        val isDone = index < dailyProgress.completedCount
                        Surface(
                            onClick = { viewModel.selectPuzzle(index) },
                            shape = RoundedCornerShape(12.dp),
                            color = when {
                                isSelected -> GamePink
                                isDone -> GameGreen.copy(alpha = 0.2f)
                                else -> Color.White
                            },
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) GamePink else if (isDone) GameGreen else Color(0xFFE2E8F0)
                            ),
                            modifier = Modifier
                                .size(42.dp)
                                .testTag("daily_puzzle_tab_$index")
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                if (isDone && !isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = GameGreen,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else {
                                    Text(
                                        text = "${index + 1}",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else Color(0xFF334155)
                                    )
                                }
                            }
                        }
                    }
                }

                // Progress Bar & Bonus Reward Note
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Daily Goal: Complete all 10",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = if (dailyProgress.completedCount == 10) "🎉 Bonus Claimed!" else "Earn +200 bonus coins upon completion",
                                fontSize = 11.sp,
                                color = if (dailyProgress.completedCount == 10) GameGreen else Color(0xFF64748B)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFFEF3C7)
                        ) {
                            Text(
                                text = "${dailyProgress.completedCount}/10",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFFB45309),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // Title & Question
                Text(
                    text = currentDailyPuzzle.levelData.title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = currentDailyPuzzle.levelData.question,
                    fontSize = 13.sp,
                    color = Color(0xFF64748B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                )

                // Visualizer
                PuzzleVisualizer(
                    level = currentDailyPuzzle.levelData,
                    currentInput = currentInput
                )
            }

            // Keypad
            NumberKeypad(
                onNumberClick = { viewModel.onDigitClick(it) },
                onBackspace = { viewModel.onBackspace() },
                onClear = { viewModel.onClear() },
                onSubmit = { viewModel.onSubmit() },
                canSubmit = currentInput.isNotEmpty(),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }

    if (isCompletedDialogVisible) {
        Dialog(onDismissRequest = { viewModel.nextDailyPuzzle() }) {
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("daily_complete_dialog")
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = GameGreen.copy(alpha = 0.2f),
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = GameGreen,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "PUZZLE ${currentIndex + 1} COMPLETE!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF0F172A)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentDailyPuzzle.levelData.explanation,
                        fontSize = 13.sp,
                        color = Color(0xFF475569),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { viewModel.nextDailyPuzzle() },
                        colors = ButtonDefaults.buttonColors(containerColor = GamePink),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(
                            text = if (currentIndex < 9) "NEXT DAILY PUZZLE" else "FINISH",
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }
    }
}
