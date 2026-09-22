package com.example.data.repository

import com.example.data.local.DailyProgressEntity
import com.example.data.local.LevelProgressEntity
import com.example.data.local.MathCrossProgressEntity
import com.example.data.local.ProgressDao
import com.example.data.local.UserStatsEntity
import com.example.data.model.AllLevelsData
import com.example.data.model.DailyChallengeData
import com.example.data.model.LevelData
import com.example.data.model.MathCrossData
import com.example.data.model.MathCrossLevel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(private val dao: ProgressDao) {

    val allLevels: List<LevelData> = AllLevelsData.levels

    val levelProgressFlow: Flow<Map<Int, LevelProgressEntity>> =
        dao.getAllLevelProgress().map { list -> list.associateBy { it.levelId } }

    val userStatsFlow: Flow<UserStatsEntity> =
        dao.getUserStatsFlow().map { it ?: UserStatsEntity() }

    val mathCrossProgressFlow: Flow<Set<Int>> =
        dao.getAllMathCrossProgress().map { list ->
            list.filter { it.completed }.map { it.crossId }.toSet()
        }

    fun getDailyProgressFlow(dateKey: String = DailyChallengeData.getTodayKey()): Flow<DailyProgressEntity> =
        dao.getDailyProgressFlow(dateKey).map { it ?: DailyProgressEntity(dateKey = dateKey) }

    suspend fun getOrCreateStats(): UserStatsEntity {
        return dao.getUserStats() ?: run {
            val initial = UserStatsEntity()
            dao.saveUserStats(initial)
            initial
        }
    }

    suspend fun completeLevel(levelId: Int, stars: Int, hintsUsed: Int, attempts: Int): Int {
        val existing = dao.getLevelProgress(levelId)
        val bestStars = if (existing != null) maxOf(existing.stars, stars) else stars
        val coinsEarned = when (stars) {
            3 -> 25
            2 -> 15
            else -> 10
        }

        val updatedProgress = LevelProgressEntity(
            levelId = levelId,
            completed = true,
            stars = bestStars,
            hintsUsed = hintsUsed,
            attempts = (existing?.attempts ?: 0) + attempts,
            unlocked = true,
            completedAt = System.currentTimeMillis()
        )
        dao.saveLevelProgress(updatedProgress)

        // Unlock next level
        val nextLevelId = levelId + 1
        if (nextLevelId <= AllLevelsData.levels.size) {
            val nextProg = dao.getLevelProgress(nextLevelId)
            if (nextProg == null || !nextProg.unlocked) {
                dao.saveLevelProgress(
                    nextProg?.copy(unlocked = true) ?: LevelProgressEntity(
                        levelId = nextLevelId,
                        unlocked = true
                    )
                )
            }
        }

        // Update stats
        val currentStats = getOrCreateStats()
        val newHighest = maxOf(currentStats.highestUnlockedLevel, nextLevelId)
        dao.saveUserStats(
            currentStats.copy(
                coins = currentStats.coins + coinsEarned,
                highestUnlockedLevel = minOf(newHighest, 50)
            )
        )

        return coinsEarned
    }

    suspend fun deductCoins(amount: Int): Boolean {
        val current = getOrCreateStats()
        if (current.coins >= amount) {
            dao.saveUserStats(current.copy(coins = current.coins - amount))
            return true
        }
        return false
    }

    suspend fun updateSettings(sound: Boolean, music: Boolean, haptic: Boolean) {
        val current = getOrCreateStats()
        dao.saveUserStats(
            current.copy(
                soundEnabled = sound,
                musicEnabled = music,
                hapticEnabled = haptic
            )
        )
    }

    suspend fun advanceDailyProgress(dateKey: String = DailyChallengeData.getTodayKey()): Pair<Int, Boolean> {
        val current = dao.getDailyProgress(dateKey) ?: DailyProgressEntity(dateKey = dateKey)
        val newCount = minOf(current.completedCount + 1, 10)
        var bonusAwarded = false
        var rewardClaimed = current.rewardClaimed

        if (newCount == 10 && !rewardClaimed) {
            rewardClaimed = true
            bonusAwarded = true
            val stats = getOrCreateStats()
            dao.saveUserStats(stats.copy(coins = stats.coins + 200)) // 200 bonus coins for 10/10!
        }

        dao.saveDailyProgress(
            current.copy(
                completedCount = newCount,
                rewardClaimed = rewardClaimed
            )
        )
        return Pair(newCount, bonusAwarded)
    }

    suspend fun completeMathCross(crossId: Int) {
        dao.saveMathCrossProgress(
            MathCrossProgressEntity(
                crossId = crossId,
                completed = true,
                completedAt = System.currentTimeMillis()
            )
        )
        val stats = getOrCreateStats()
        dao.saveUserStats(stats.copy(coins = stats.coins + 20))
    }

    suspend fun resetAllProgress() {
        dao.clearLevelProgress()
        dao.clearDailyProgress()
        dao.clearMathCrossProgress()
        val initial = UserStatsEntity(
            coins = 100,
            highestUnlockedLevel = 1,
            soundEnabled = true,
            musicEnabled = true,
            hapticEnabled = true
        )
        dao.saveUserStats(initial)
        dao.saveLevelProgress(LevelProgressEntity(levelId = 1, unlocked = true))
    }
}
