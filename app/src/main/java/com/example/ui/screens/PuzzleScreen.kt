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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.NumberKeypad
import com.example.ui.components.PuzzleVisualizer
import com.example.ui.dialogs.HintDialog
import com.example.ui.dialogs.LevelCompleteDialog
import com.example.ui.theme.GameCanvas
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GamePink
import com.example.ui.theme.GamePurple
import com.example.ui.theme.GamePurpleBg
import com.example.ui.theme.GamePurpleDark
import com.example.ui.viewmodel.PuzzleViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzleScreen(
    viewModel: PuzzleViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val level by viewModel.level.collectAsState()
    val currentInput by viewModel.currentInput.collectAsState()
    val userStats by viewModel.userStats.collectAsState()
    val unlockedHintTier by viewModel.unlockedHintTier.collectAsState()
    val isHintDialogVisible by viewModel.isHintDialogVisible.collectAsState()
    val isCompleteDialogVisible by viewModel.isCompleteDialogVisible.collectAsState()
    val showErrorShake by viewModel.showErrorShake.collectAsState()
    val starsEarned by viewModel.starsEarned.collectAsState()
    val coinsEarned by viewModel.coinsEarned.collectAsState()

    // Shake animation on error
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
                            text = "Level ${level.id}",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = level.difficulty.displayName,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GamePurple
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("puzzle_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF0F172A)
                        )
                    }
                },
                actions = {
                    // Hint Button
                    Surface(
                        onClick = { viewModel.openHintDialog() },
                        shape = RoundedCornerShape(14.dp),
                        color = GameGold.copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GameGold),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .testTag("puzzle_hint_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "Hint",
                                tint = GameGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "HINT",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF92400E)
                            )
                        }
                    }

                    // Coins indicator
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
                    .padding(top = 10.dp)
                    .offset { IntOffset(shakeOffset.value.roundToInt(), 0) }
            ) {
                // Puzzle Title & Question
                Text(
                    text = level.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF0F172A),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = level.question,
                    fontSize = 13.sp,
                    color = Color(0xFF64748B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )

                // Visualizer
                PuzzleVisualizer(
                    level = level,
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

    // Hint Dialog
    if (isHintDialogVisible) {
        HintDialog(
            hint1 = level.hint1,
            hint2 = level.hint2,
            hint3 = level.hint3,
            unlockedTier = unlockedHintTier,
            userCoins = userStats.coins,
            onUnlockTier = { tier, cost ->
                viewModel.unlockHintTier(tier, cost)
            },
            onDismiss = { viewModel.closeHintDialog() }
        )
    }

    // Win / Level Complete Dialog
    if (isCompleteDialogVisible) {
        LevelCompleteDialog(
            levelId = level.id,
            stars = starsEarned,
            coinsEarned = coinsEarned,
            explanation = level.explanation,
            onNextLevel = { viewModel.nextLevel() },
            onReplay = { viewModel.replayLevel() },
            onDismiss = { /* Non-dismissible without choice */ }
        )
    }
}
