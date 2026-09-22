package com.example.data.model

enum class PuzzleType {
    RELATIONSHIP,
    GEOMETRIC_TRIANGLE,
    GEOMETRIC_CIRCLE,
    GEOMETRIC_DIAMOND,
    GEOMETRIC_HEXAGON,
    GEOMETRIC_CROSS,
    NUMBER_PATTERN,
    NUMBER_GRID,
    MATH_CROSS,
    MIXED_LOGIC
}

enum class Difficulty(val displayName: String) {
    VERY_EASY("Very Easy"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    EXPERT("Expert")
}

data class LevelData(
    val id: Int,
    val title: String,
    val type: PuzzleType,
    val difficulty: Difficulty,
    val question: String,
    val equations: List<String> = emptyList(),
    val numbers: List<Int> = emptyList(),
    val gridData: List<List<String>> = emptyList(),
    val shapeLabels: List<String> = emptyList(),
    val correctAnswer: Int,
    val hint1: String,
    val hint2: String,
    val hint3: String,
    val explanation: String
)

data class MathCrossLevel(
    val id: Int,
    val title: String,
    val difficulty: Difficulty,
    val gridRows: List<List<String>>, // e.g. ["8", "+", "7", "=", "15"], ["÷", "", "-", "", "-"], etc.
    val targetRow: Int,
    val targetCol: Int,
    val correctAnswer: Int,
    val hint1: String,
    val hint2: String,
    val explanation: String
)

data class DailyPuzzle(
    val dayIndex: Int, // 1 to 10
    val levelData: LevelData
)
