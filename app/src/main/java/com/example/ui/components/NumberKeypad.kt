package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GamePink
import com.example.ui.theme.GamePurple
import com.example.ui.theme.GameRed

@Composable
fun NumberKeypad(
    onNumberClick: (Int) -> Unit,
    onBackspace: () -> Unit,
    onClear: () -> Unit,
    onSubmit: () -> Unit,
    canSubmit: Boolean,
    modifier: Modifier = Modifier
) {
    val keySpacing = 8.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(keySpacing)
    ) {
        // Row 1: 1, 2, 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(keySpacing)
        ) {
            KeypadDigitButton(digit = 1, onClick = onNumberClick, modifier = Modifier.weight(1f))
            KeypadDigitButton(digit = 2, onClick = onNumberClick, modifier = Modifier.weight(1f))
            KeypadDigitButton(digit = 3, onClick = onNumberClick, modifier = Modifier.weight(1f))
        }

        // Row 2: 4, 5, 6
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(keySpacing)
        ) {
            KeypadDigitButton(digit = 4, onClick = onNumberClick, modifier = Modifier.weight(1f))
            KeypadDigitButton(digit = 5, onClick = onNumberClick, modifier = Modifier.weight(1f))
            KeypadDigitButton(digit = 6, onClick = onNumberClick, modifier = Modifier.weight(1f))
        }

        // Row 3: 7, 8, 9
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(keySpacing)
        ) {
            KeypadDigitButton(digit = 7, onClick = onNumberClick, modifier = Modifier.weight(1f))
            KeypadDigitButton(digit = 8, onClick = onNumberClick, modifier = Modifier.weight(1f))
            KeypadDigitButton(digit = 9, onClick = onNumberClick, modifier = Modifier.weight(1f))
        }

        // Row 4: Clear (C), 0, Backspace (⌫)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(keySpacing)
        ) {
            // Clear Key
            Surface(
                onClick = onClear,
                shape = RoundedCornerShape(16.dp),
                color = GameRed.copy(alpha = 0.12f),
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp)
                    .testTag("keypad_clear")
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "C",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = GameRed
                    )
                }
            }

            // Digit 0
            KeypadDigitButton(digit = 0, onClick = onNumberClick, modifier = Modifier.weight(1f))

            // Backspace Key
            Surface(
                onClick = onBackspace,
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF1F5F9),
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp)
                    .testTag("keypad_backspace")
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Backspace,
                        contentDescription = "Backspace",
                        tint = Color(0xFF475569)
                    )
                }
            }
        }

        // Row 5: Large prominent Submit Button
        Surface(
            onClick = { if (canSubmit) onSubmit() },
            enabled = canSubmit,
            shape = RoundedCornerShape(18.dp),
            color = if (canSubmit) GameGreen else Color(0xFFE2E8F0),
            shadowElevation = if (canSubmit) 6.dp else 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("keypad_submit")
        ) {
            Box(contentAlignment = Alignment.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Submit Answer",
                        tint = if (canSubmit) Color.White else Color(0xFF94A3B8)
                    )
                    Text(
                        text = "CHECK ANSWER",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp,
                        color = if (canSubmit) Color.White else Color(0xFF94A3B8)
                    )
                }
            }
        }
    }
}

@Composable
fun KeypadDigitButton(
    digit: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = { onClick(digit) },
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        modifier = modifier
            .height(54.dp)
            .testTag("keypad_digit_$digit")
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "$digit",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )
        }
    }
}
