package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {

    @Query("SELECT * FROM level_progress ORDER BY levelId ASC")
    fun getAllLevelProgress(): Flow<List<LevelProgressEntity>>

    @Query("SELECT * FROM level_progress WHERE levelId = :levelId")
    suspend fun getLevelProgress(levelId: Int): LevelProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLevelProgress(progress: LevelProgressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllLevelProgress(list: List<LevelProgressEntity>)

    @Query("SELECT * FROM user_stats WHERE id = 1")
    fun getUserStatsFlow(): Flow<UserStatsEntity?>

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStats(): UserStatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserStats(stats: UserStatsEntity)

    @Query("SELECT * FROM daily_progress WHERE dateKey = :dateKey")
    fun getDailyProgressFlow(dateKey: String): Flow<DailyProgressEntity?>

    @Query("SELECT * FROM daily_progress WHERE dateKey = :dateKey")
    suspend fun getDailyProgress(dateKey: String): DailyProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDailyProgress(daily: DailyProgressEntity)

    @Query("SELECT * FROM math_cross_progress")
    fun getAllMathCrossProgress(): Flow<List<MathCrossProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMathCrossProgress(progress: MathCrossProgressEntity)

    @Query("DELETE FROM level_progress")
    suspend fun clearLevelProgress()

    @Query("DELETE FROM daily_progress")
    suspend fun clearDailyProgress()

    @Query("DELETE FROM math_cross_progress")
    suspend fun clearMathCrossProgress()
}
