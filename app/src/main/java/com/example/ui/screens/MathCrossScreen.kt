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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.window.Dialog
import com.example.ui.components.MissingValueTargetBadge
import com.example.ui.components.NumberKeypad
import com.example.ui.theme.GameCanvas
import com.example.ui.theme.GameCyan
import com.example.ui.theme.GameCyanDark
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GamePurple
import com.example.ui.theme.GamePurpleBg
import com.example.ui.theme.GamePurpleDark
import com.example.ui.viewmodel.MathCrossViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathCrossScreen(
    viewModel: MathCrossViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedLevel by viewModel.selectedLevel.collectAsState()
    val completedIds by viewModel.completedCrossIds.collectAsState()
    val userStats by viewModel.userStats.collectAsState()
    val currentInput by viewModel.currentInput.collectAsState()
    val isCompletedDialogVisible by viewModel.isCompletedDialogVisible.collectAsState()
    val showErrorShake by viewModel.showErrorShake.collectAsState()

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
                            text = "Math Cross",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "Puzzle ${selectedLevel.id} • ${selectedLevel.difficulty.displayName}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GameCyanDark
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("math_cross_back_button")
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
                // Horizontal Cross Puzzle Selector
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(viewModel.allCrossLevels) { index, cross ->
                        val isSelected = cross.id == selectedLevel.id
                        val isDone = completedIds.contains(cross.id)
                        Surface(
                            onClick = { viewModel.selectLevel(cross) },
                            shape = RoundedCornerShape(12.dp),
                            color = when {
                                isSelected -> GameCyan
                                isDone -> GameGreen.copy(alpha = 0.2f)
                                else -> Color.White
                            },
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) GameCyan else if (isDone) GameGreen else Color(0xFFE2E8F0)
                            ),
                            modifier = Modifier
                                .size(42.dp)
                                .testTag("cross_puzzle_tab_${cross.id}")
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
                                        text = "${cross.id}",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else Color(0xFF334155)
                                    )
                                }
                            }
                        }
                    }
                }

                Text(
                    text = selectedLevel.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(top = 6.dp)
                )

                Text(
                    text = "Ensure intersecting horizontal and vertical equations are true.",
                    fontSize = 13.sp,
                    color = Color(0xFF64748B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp)
                )

                // Crossword Grid Card
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        selectedLevel.gridRows.forEachIndexed { rIndex, row ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                row.forEachIndexed { cIndex, cell ->
                                    if (rIndex == selectedLevel.targetRow && cIndex == selectedLevel.targetCol) {
                                        MissingValueTargetBadge(
                                            currentInput = currentInput,
                                            isSmall = true
                                        )
                                    } else if (cell.isEmpty()) {
                                        Spacer(modifier = Modifier.size(46.dp))
                                    } else {
                                        val isOp = cell in listOf("+", "-", "×", "÷", "=")
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = if (isOp) Color(0xFFF1F5F9) else GamePurpleBg,
                                            border = if (!isOp) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDD6FE)) else null,
                                            modifier = Modifier.size(if (isOp) 38.dp else 46.dp)
                                        ) {
                                            Box(contentAlignment = Alignment.Center) {
                                                Text(
                                                    text = cell,
                                                    fontSize = if (isOp) 18.sp else 20.sp,
                                                    fontWeight = if (isOp) FontWeight.Black else FontWeight.Bold,
                                                    color = if (isOp) Color(0xFF64748B) else GamePurpleDark
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
        Dialog(onDismissRequest = { viewModel.nextCrossLevel() }) {
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("cross_complete_dialog")
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
                        text = "CROSSWORD SOLVED!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF0F172A)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = selectedLevel.explanation,
                        fontSize = 13.sp,
                        color = Color(0xFF475569),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFEF3C7)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🪙", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "+20 COINS EARNED",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { viewModel.nextCrossLevel() },
                        colors = ButtonDefaults.buttonColors(containerColor = GameCyan),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(
                            text = "CONTINUE",
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }
    }
}
