package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level_progress")
data class LevelProgressEntity(
    @PrimaryKey val levelId: Int,
    val completed: Boolean = false,
    val stars: Int = 0,
    val hintsUsed: Int = 0,
    val attempts: Int = 0,
    val unlocked: Boolean = false,
    val completedAt: Long = 0L
)

@Entity(tableName = "daily_progress")
data class DailyProgressEntity(
    @PrimaryKey val dateKey: String,
    val completedCount: Int = 0, // 0 to 10
    val rewardClaimed: Boolean = false
)

@Entity(tableName = "math_cross_progress")
data class MathCrossProgressEntity(
    @PrimaryKey val crossId: Int,
    val completed: Boolean = false,
    val completedAt: Long = 0L
)

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey val id: Int = 1,
    val coins: Int = 100, // Starts with 100 coins
    val highestUnlockedLevel: Int = 1,
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true,
    val hapticEnabled: Boolean = true
)
