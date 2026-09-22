package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LevelData
import com.example.data.model.PuzzleType
import com.example.ui.theme.GameCyan
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GamePink
import com.example.ui.theme.GamePurple
import com.example.ui.theme.GamePurpleBg
import com.example.ui.theme.GamePurpleDark

@Composable
fun PuzzleVisualizer(
    level: LevelData,
    currentInput: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("puzzle_visualizer_card")
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            when (level.type) {
                PuzzleType.GEOMETRIC_TRIANGLE -> {
                    GeometricTriangleView(level = level, currentInput = currentInput)
                }
                PuzzleType.GEOMETRIC_CIRCLE -> {
                    GeometricCircleView(level = level, currentInput = currentInput)
                }
                PuzzleType.GEOMETRIC_DIAMOND -> {
                    GeometricDiamondView(level = level, currentInput = currentInput)
                }
                PuzzleType.GEOMETRIC_HEXAGON -> {
                    GeometricHexagonView(level = level, currentInput = currentInput)
                }
                PuzzleType.GEOMETRIC_CROSS -> {
                    GeometricCrossView(level = level, currentInput = currentInput)
                }
                PuzzleType.NUMBER_GRID, PuzzleType.MATH_CROSS -> {
                    NumberGridView(level = level, currentInput = currentInput)
                }
                PuzzleType.NUMBER_PATTERN -> {
                    NumberPatternView(level = level, currentInput = currentInput)
                }
                PuzzleType.RELATIONSHIP, PuzzleType.MIXED_LOGIC -> {
                    EquationsView(level = level, currentInput = currentInput)
                }
            }
        }
    }
}

@Composable
private fun GeometricTriangleView(level: LevelData, currentInput: String) {
    Box(
        modifier = Modifier
            .size(240.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        // Draw triangle perimeter wireframe
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                moveTo(size.width / 2f, 24f)
                lineTo(24f, size.height - 24f)
                lineTo(size.width - 24f, size.height - 24f)
                close()
            }
            drawPath(
                path = path,
                color = Color(0xFFE2E8F0),
                style = Stroke(width = 8f)
            )
        }

        // Top Apex Node
        val apexVal = level.numbers.getOrNull(0)?.toString() ?: (level.shapeLabels.getOrNull(0) ?: "4")
        val leftVal = level.numbers.getOrNull(1)?.toString() ?: (level.shapeLabels.getOrNull(1) ?: "5")
        val rightVal = level.numbers.getOrNull(2)?.toString() ?: (level.shapeLabels.getOrNull(2) ?: "6")

        ValueCircleBadge(
            text = apexVal.filter { it.isDigit() },
            color = GamePurple,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // Bottom Left Node
        ValueCircleBadge(
            text = leftVal.filter { it.isDigit() },
            color = GameCyan,
            modifier = Modifier.align(Alignment.BottomStart)
        )

        // Bottom Right Node
        ValueCircleBadge(
            text = rightVal.filter { it.isDigit() },
            color = GamePink,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

        // Center Target Card
        MissingValueTargetBadge(
            currentInput = currentInput,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun GeometricCircleView(level: LevelData, currentInput: String) {
    Box(
        modifier = Modifier
            .size(240.dp)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFF1F5F9),
                style = Stroke(width = 16f)
            )
            drawLine(
                color = Color(0xFFCBD5E1),
                start = Offset(size.width / 2f, 0f),
                end = Offset(size.width / 2f, size.height),
                strokeWidth = 4f
            )
            drawLine(
                color = Color(0xFFCBD5E1),
                start = Offset(0f, size.height / 2f),
                end = Offset(size.width, size.height / 2f),
                strokeWidth = 4f
            )
        }

        // Sector values
        val n0 = level.numbers.getOrNull(0)?.toString() ?: "3"
        val n1 = level.numbers.getOrNull(1)?.toString() ?: "7"
        val n2 = level.numbers.getOrNull(2)?.toString() ?: "4"

        ValueCircleBadge(text = n0, color = GamePurple, modifier = Modifier.align(Alignment.TopCenter))
        ValueCircleBadge(text = n1, color = GameCyan, modifier = Modifier.align(Alignment.BottomCenter))
        ValueCircleBadge(text = n2, color = GamePink, modifier = Modifier.align(Alignment.CenterStart))

        MissingValueTargetBadge(
            currentInput = currentInput,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun GeometricDiamondView(level: LevelData, currentInput: String) {
    Box(
        modifier = Modifier
            .size(240.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        val n0 = level.numbers.getOrNull(0)?.toString() ?: "4"
        val n1 = level.numbers.getOrNull(1)?.toString() ?: "7"
        val n2 = level.numbers.getOrNull(2)?.toString() ?: "3"
        val n3 = level.numbers.getOrNull(3)?.toString() ?: "6"

        ValueCircleBadge(text = n0, color = GamePurple, modifier = Modifier.align(Alignment.TopCenter))
        ValueCircleBadge(text = n1, color = GameCyan, modifier = Modifier.align(Alignment.CenterEnd))
        ValueCircleBadge(text = n2, color = GamePink, modifier = Modifier.align(Alignment.CenterStart))
        ValueCircleBadge(text = n3, color = GameGreen, modifier = Modifier.align(Alignment.BottomCenter))

        MissingValueTargetBadge(
            currentInput = currentInput,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun GeometricHexagonView(level: LevelData, currentInput: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (level.shapeLabels.isNotEmpty()) {
            level.shapeLabels.forEach { label ->
                Surface(
                    color = GamePurpleBg,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = label,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = GamePurpleDark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                level.numbers.take(4).forEach { num ->
                    ValueCircleBadge(text = num.toString(), color = GamePurple)
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        MissingValueTargetBadge(currentInput = currentInput)
    }
}

@Composable
private fun GeometricCrossView(level: LevelData, currentInput: String) {
    Box(
        modifier = Modifier
            .size(240.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        val n0 = level.numbers.getOrNull(0)?.toString() ?: "8"
        val n1 = level.numbers.getOrNull(1)?.toString() ?: "4"
        val n2 = level.numbers.getOrNull(2)?.toString() ?: "9"
        val n3 = level.numbers.getOrNull(3)?.toString() ?: "4"

        ValueCircleBadge(text = n0, color = GamePurple, modifier = Modifier.align(Alignment.TopCenter))
        ValueCircleBadge(text = n1, color = GameCyan, modifier = Modifier.align(Alignment.BottomCenter))
        ValueCircleBadge(text = n2, color = GamePink, modifier = Modifier.align(Alignment.CenterStart))
        ValueCircleBadge(text = n3, color = GameGold, modifier = Modifier.align(Alignment.CenterEnd))

        MissingValueTargetBadge(
            currentInput = currentInput,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun NumberGridView(level: LevelData, currentInput: String) {
    val grid = level.gridData
    if (grid.isEmpty()) return

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        grid.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { cell ->
                    if (cell == "?") {
                        MissingValueTargetBadge(
                            currentInput = currentInput,
                            isSmall = true
                        )
                    } else if (cell.isEmpty()) {
                        Spacer(modifier = Modifier.size(46.dp))
                    } else {
                        val isOperator = cell in listOf("+", "-", "×", "÷", "=")
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isOperator) Color(0xFFF1F5F9) else GamePurpleBg,
                            border = if (!isOperator) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDD6FE)) else null,
                            modifier = Modifier.size(if (isOperator) 38.dp else 46.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = cell,
                                    fontSize = if (isOperator) 18.sp else 20.sp,
                                    fontWeight = if (isOperator) FontWeight.Black else FontWeight.Bold,
                                    color = if (isOperator) Color(0xFF64748B) else GamePurpleDark
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NumberPatternView(level: LevelData, currentInput: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        // Display sequence elements
        if (level.numbers.isNotEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                level.numbers.forEachIndexed { index, num ->
                    ValueCircleBadge(
                        text = num.toString(),
                        color = if (index % 2 == 0) GamePurple else GameCyan,
                        isSmall = true
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFFCBD5E1),
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(18.dp)
                    )
                }
                MissingValueTargetBadge(currentInput = currentInput, isSmall = true)
            }
        } else if (level.equations.isNotEmpty()) {
            level.equations.forEach { eq ->
                Surface(
                    color = GamePurpleBg,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(
                        text = eq,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = GamePurpleDark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            MissingValueTargetBadge(currentInput = currentInput)
        }
    }
}

@Composable
private fun EquationsView(level: LevelData, currentInput: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        level.equations.forEach { equation ->
            val hasQuestion = equation.contains("?")
            Surface(
                color = if (hasQuestion) GameGold.copy(alpha = 0.15f) else Color(0xFFF8FAFC),
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (hasQuestion) GameGold else Color(0xFFE2E8F0)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = equation,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (hasQuestion) GameGold else Color(0xFF1E293B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        MissingValueTargetBadge(currentInput = currentInput)
    }
}

@Composable
fun ValueCircleBadge(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    isSmall: Boolean = false
) {
    val size = if (isSmall) 42.dp else 52.dp
    val fontSize = if (isSmall) 16.sp else 20.sp

    Surface(
        shape = CircleShape,
        color = color,
        shadowElevation = 4.dp,
        modifier = modifier.size(size)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        }
    }
}

@Composable
fun MissingValueTargetBadge(
    currentInput: String,
    modifier: Modifier = Modifier,
    isSmall: Boolean = false
) {
    val size = if (isSmall) 50.dp else 68.dp
    val textToShow = if (currentInput.isEmpty()) "?" else currentInput
    val isAnswerFilled = currentInput.isNotEmpty()

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isAnswerFilled) GamePink else Color(0xFFFEF3C7),
        border = androidx.compose.foundation.BorderStroke(
            2.5.dp,
            if (isAnswerFilled) GamePink else GameGold
        ),
        shadowElevation = 6.dp,
        modifier = modifier
            .size(size)
            .testTag("target_answer_box")
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = textToShow,
                fontSize = if (isSmall) 20.sp else 26.sp,
                fontWeight = FontWeight.Black,
                color = if (isAnswerFilled) Color.White else Color(0xFFB45309)
            )
        }
    }
}
