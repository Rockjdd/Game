package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.HapticManager
import com.example.audio.SoundManager
import com.example.data.local.UserStatsEntity
import com.example.data.model.MathCrossData
import com.example.data.model.MathCrossLevel
import com.example.data.repository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MathCrossViewModel(
    private val repository: GameRepository,
    private val soundManager: SoundManager,
    private val hapticManager: HapticManager
) : ViewModel() {

    val allCrossLevels: List<MathCrossLevel> = MathCrossData.levels

    val completedCrossIds: StateFlow<Set<Int>> =
        repository.mathCrossProgressFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    val userStats: StateFlow<UserStatsEntity> =
        repository.userStatsFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserStatsEntity()
        )

    private val _selectedLevel = MutableStateFlow(MathCrossData.levels.first())
    val selectedLevel: StateFlow<MathCrossLevel> = _selectedLevel.asStateFlow()

    private val _currentInput = MutableStateFlow("")
    val currentInput: StateFlow<String> = _currentInput.asStateFlow()

    private val _isCompletedDialogVisible = MutableStateFlow(false)
    val isCompletedDialogVisible: StateFlow<Boolean> = _isCompletedDialogVisible.asStateFlow()

    private val _showErrorShake = MutableStateFlow(false)
    val showErrorShake: StateFlow<Boolean> = _showErrorShake.asStateFlow()

    fun selectLevel(level: MathCrossLevel) {
        _selectedLevel.value = level
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
        if (inputVal == _selectedLevel.value.correctAnswer) {
            soundManager.playCorrect()
            hapticManager.success()
            viewModelScope.launch {
                repository.completeMathCross(_selectedLevel.value.id)
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

    fun nextCrossLevel() {
        val nextIndex = allCrossLevels.indexOfFirst { it.id == _selectedLevel.value.id } + 1
        if (nextIndex < allCrossLevels.size) {
            selectLevel(allCrossLevels[nextIndex])
        } else {
            _isCompletedDialogVisible.value = false
        }
    }
}
