package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.DailyProgressEntity
import com.example.data.local.LevelProgressEntity
import com.example.data.local.UserStatsEntity
import com.example.data.model.AllLevelsData
import com.example.data.model.DailyChallengeData
import com.example.data.model.LevelData
import com.example.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GameRepository) : ViewModel() {

    val allLevels: List<LevelData> = repository.allLevels

    val progressMap: StateFlow<Map<Int, LevelProgressEntity>> =
        repository.levelProgressFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

    val userStats: StateFlow<UserStatsEntity> =
        repository.userStatsFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserStatsEntity()
        )

    val dailyProgress: StateFlow<DailyProgressEntity> =
        repository.getDailyProgressFlow().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DailyProgressEntity(dateKey = DailyChallengeData.getTodayKey())
        )

    val mathCrossProgress: StateFlow<Set<Int>> =
        repository.mathCrossProgressFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    fun updateSettings(sound: Boolean, music: Boolean, haptic: Boolean) {
        viewModelScope.launch {
            repository.updateSettings(sound, music, haptic)
        }
    }

    fun resetAllProgress() {
        viewModelScope.launch {
            repository.resetAllProgress()
        }
    }

    fun addBonusCoins(amount: Int) {
        viewModelScope.launch {
            val stats = repository.getOrCreateStats()
            repository.updateSettings(stats.soundEnabled, stats.musicEnabled, stats.hapticEnabled)
        }
    }
}
