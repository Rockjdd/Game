package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.HapticManager
import com.example.audio.SoundManager
import com.example.data.local.DailyProgressEntity
import com.example.data.local.UserStatsEntity
import com.example.data.model.DailyChallengeData
import com.example.data.model.DailyPuzzle
import com.example.data.repository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DailyChallengeViewModel(
    private val repository: GameRepository,
    private val soundManager: SoundManager,
    private val hapticManager: HapticManager
) : ViewModel() {

    val todayKey = DailyChallengeData.getTodayKey()
    val dailyPuzzles: List<DailyPuzzle> = DailyChallengeData.getDailyPuzzlesForDate(todayKey)

    val dailyProgress: StateFlow<DailyProgressEntity> =
        repository.getDailyProgressFlow(todayKey).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DailyProgressEntity(dateKey = todayKey)
        )

    val userStats: StateFlow<UserStatsEntity> =
        repository.userStatsFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserStatsEntity()
        )

    private val _currentPuzzleIndex = MutableStateFlow(0)
    val currentPuzzleIndex: StateFlow<Int> = _currentPuzzleIndex.asStateFlow()

    private val _currentInput = MutableStateFlow("")
    val currentInput: StateFlow<String> = _currentInput.asStateFlow()

    private val _isCompletedDialogVisible = MutableStateFlow(false)
    val isCompletedDialogVisible: StateFlow<Boolean> = _isCompletedDialogVisible.asStateFlow()

    private val _showErrorShake = MutableStateFlow(false)
    val showErrorShake: StateFlow<Boolean> = _showErrorShake.asStateFlow()

    val currentDailyPuzzle: DailyPuzzle
        get() = dailyPuzzles.getOrElse(_currentPuzzleIndex.value) { dailyPuzzles.first() }

    fun selectPuzzle(index: Int) {
        _currentPuzzleIndex.value = index.coerceIn(0, dailyPuzzles.size - 1)
        _currentInput.value = ""
        _isCompletedDialogVisible.value = false
        _showErrorShake.value = false
    }

    fun onDigitClick(digit: Int) {
        soundManager.playClick()
        hapticManager.click()
        if (_currentInput.value.length < 5) {
            _currentInput.value += digit.toString()
        }
    }

    fun onBackspace() {
        soundManager.playClick()
        hapticManager.click()
        if (_currentInput.value.isNotEmpty()) {
            _currentInput.value = _currentInput.value.dropLast(1)
        }
    }

    fun onClear() {
        soundManager.playClick()
        hapticManager.click()
        _currentInput.value = ""
    }

    fun onSubmit() {
        val inputVal = _currentInput.value.toIntOrNull() ?: return
        val current = currentDailyPuzzle.levelData
        if (inputVal == current.correctAnswer) {
            soundManager.playCorrect()
            hapticManager.success()
            viewModelScope.launch {
                repository.advanceDailyProgress(todayKey)
                delay(300)
                soundManager.playLevelComplete()
                _isCompletedDialogVisible.value = true
            }
        } else {
            soundManager.playWrong()
            hapticManager.error()
            viewModelScope.launch {
                _showErrorShake.value = true
                delay(600)
                _showErrorShake.value = false
                _currentInput.value = ""
            }
        }
    }

    fun nextDailyPuzzle() {
        if (_currentPuzzleIndex.value < dailyPuzzles.size - 1) {
            selectPuzzle(_currentPuzzleIndex.value + 1)
        } else {
            _isCompletedDialogVisible.value = false
        }
    }
}
