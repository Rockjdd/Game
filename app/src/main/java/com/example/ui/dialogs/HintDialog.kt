package com.example.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameGreen
import com.example.ui.theme.GamePurple

@Composable
fun HintDialog(
    hint1: String,
    hint2: String,
    hint3: String,
    unlockedTier: Int, // 0, 1, 2, or 3
    userCoins: Int,
    onUnlockTier: (tier: Int, cost: Int) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("hint_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = GameGold.copy(alpha = 0.2f),
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = "Hints",
                                    tint = GameGold,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "HINTS & CLUES",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF0F172A)
                        )
                    }

                    // Coins indicator
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFEF3C7)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🪙", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$userCoins",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Tier 1: Subtle Clue (10 Coins)
                HintTierCard(
                    tier = 1,
                    title = "Tier 1: General Clue",
                    cost = 10,
                    content = hint1,
                    isUnlocked = unlockedTier >= 1,
                    canAfford = userCoins >= 10,
                    onUnlock = { onUnlockTier(1, 10) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Tier 2: Step-by-Step Step (25 Coins)
                HintTierCard(
                    tier = 2,
                    title = "Tier 2: Calculation Step",
                    cost = 25,
                    content = hint2,
                    isUnlocked = unlockedTier >= 2,
                    canAfford = userCoins >= 25,
                    onUnlock = { onUnlockTier(2, 25) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Tier 3: Full Solution (50 Coins)
                HintTierCard(
                    tier = 3,
                    title = "Tier 3: Solution Revealed",
                    cost = 50,
                    content = hint3,
                    isUnlocked = unlockedTier >= 3,
                    canAfford = userCoins >= 50,
                    onUnlock = { onUnlockTier(3, 50) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
                    modifier = Modifier.fillMaxWidth().height(46.dp)
                ) {
                    Text(
                        text = "CLOSE",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF475569)
                    )
                }
            }
        }
    }
}

@Composable
private fun HintTierCard(
    tier: Int,
    title: String,
    cost: Int,
    content: String,
    isUnlocked: Boolean,
    canAfford: Boolean,
    onUnlock: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isUnlocked) Color(0xFFF8FAFC) else Color(0xFFFAF5FF),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isUnlocked) GameGreen.copy(alpha = 0.5f) else Color(0xFFDDD6FE)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isUnlocked) GameGreen else GamePurple
                )

                if (isUnlocked) {
                    Surface(
                        shape = CircleShape,
                        color = GameGreen.copy(alpha = 0.2f),
                        modifier = Modifier.size(24.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Unlocked",
                                tint = GameGreen,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                } else {
                    Button(
                        onClick = onUnlock,
                        enabled = canAfford,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GameGold,
                            disabledContainerColor = Color(0xFFCBD5E1)
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            horizontal = 10.dp,
                            vertical = 4.dp
                        ),
                        modifier = Modifier.height(32.dp).testTag("unlock_hint_tier_$tier")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🪙", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$cost",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (isUnlocked) {
                Text(
                    text = content,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF1E293B)
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (canAfford) "Tap to unlock hint with coins" else "Not enough coins",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        }
    }
}
