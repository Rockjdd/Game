package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.HapticManager
import com.example.audio.SoundManager
import com.example.data.local.UserStatsEntity
import com.example.data.model.AllLevelsData
import com.example.data.model.LevelData
import com.example.data.repository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PuzzleViewModel(
    private val initialLevelId: Int,
    private val repository: GameRepository,
    private val soundManager: SoundManager,
    private val hapticManager: HapticManager
) : ViewModel() {

    private val _level = MutableStateFlow(AllLevelsData.getLevel(initialLevelId))
    val level: StateFlow<LevelData> = _level.asStateFlow()

    private val _currentInput = MutableStateFlow("")
    val currentInput: StateFlow<String> = _currentInput.asStateFlow()

    private val _unlockedHintTier = MutableStateFlow(0)
    val unlockedHintTier: StateFlow<Int> = _unlockedHintTier.asStateFlow()

    private val _isHintDialogVisible = MutableStateFlow(false)
    val isHintDialogVisible: StateFlow<Boolean> = _isHintDialogVisible.asStateFlow()

    private val _isCompleteDialogVisible = MutableStateFlow(false)
    val isCompleteDialogVisible: StateFlow<Boolean> = _isCompleteDialogVisible.asStateFlow()

    private val _showErrorShake = MutableStateFlow(false)
    val showErrorShake: StateFlow<Boolean> = _showErrorShake.asStateFlow()

    private val _attempts = MutableStateFlow(0)
    private val _starsEarned = MutableStateFlow(3)
    val starsEarned: StateFlow<Int> = _starsEarned.asStateFlow()

    private val _coinsEarned = MutableStateFlow(0)
    val coinsEarned: StateFlow<Int> = _coinsEarned.asStateFlow()

    val userStats: StateFlow<UserStatsEntity> =
        repository.userStatsFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserStatsEntity()
        )

    init {
        loadLevel(initialLevelId)
    }

    fun loadLevel(levelId: Int) {
        _level.value = AllLevelsData.getLevel(levelId)
        _currentInput.value = ""
        _unlockedHintTier.value = 0
        _isCompleteDialogVisible.value = false
        _isHintDialogVisible.value = false
        _showErrorShake.value = false
        _attempts.value = 0
        _coinsEarned.value = 0
        _starsEarned.value = 3
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
        val expected = _level.value.correctAnswer

        if (inputVal == expected) {
            // Correct answer!
            soundManager.playCorrect()
            hapticManager.success()

            // Calculate stars:
            // 0 hints used and <= 2 attempts = 3 stars
            // 1 hint used = 2 stars
            // 2+ hints used = 1 star
            val stars = when {
                _unlockedHintTier.value == 0 && _attempts.value <= 1 -> 3
                _unlockedHintTier.value <= 1 -> 2
                else -> 1
            }
            _starsEarned.value = stars

            viewModelScope.launch {
                val earned = repository.completeLevel(
                    levelId = _level.value.id,
                    stars = stars,
                    hintsUsed = _unlockedHintTier.value,
                    attempts = _attempts.value + 1
                )
                _coinsEarned.value = earned
                delay(300)
                soundManager.playLevelComplete()
                _isCompleteDialogVisible.value = true
            }
        } else {
            // Wrong answer!
            soundManager.playWrong()
            hapticManager.error()
            _attempts.value += 1
            viewModelScope.launch {
                _showErrorShake.value = true
                delay(600)
                _showErrorShake.value = false
                _currentInput.value = ""
            }
        }
    }

    fun openHintDialog() {
        soundManager.playClick()
        hapticManager.click()
        _isHintDialogVisible.value = true
    }

    fun closeHintDialog() {
        _isHintDialogVisible.value = false
    }

    fun unlockHintTier(tier: Int, cost: Int) {
        viewModelScope.launch {
            val success = repository.deductCoins(cost)
            if (success) {
                soundManager.playCoin()
                hapticManager.click()
                _unlockedHintTier.value = maxOf(_unlockedHintTier.value, tier)
            } else {
                hapticManager.error()
            }
        }
    }

    fun nextLevel() {
        val nextId = _level.value.id + 1
        if (nextId <= AllLevelsData.levels.size) {
            loadLevel(nextId)
        } else {
            _isCompleteDialogVisible.value = false
        }
    }

    fun replayLevel() {
        loadLevel(_level.value.id)
    }
}
