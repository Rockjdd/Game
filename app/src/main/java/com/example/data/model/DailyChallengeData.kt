package com.example.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DailyChallengeData {

    fun getTodayKey(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    // 10 Progressive daily puzzles with clear math rules
    fun getDailyPuzzlesForDate(dateKey: String): List<DailyPuzzle> {
        val seed = dateKey.hashCode()
        val shift = Math.abs(seed % 5)

        return listOf(
            DailyPuzzle(
                dayIndex = 1,
                levelData = LevelData(
                    id = 101,
                    title = "Daily 1: Quick Add",
                    type = PuzzleType.RELATIONSHIP,
                    difficulty = Difficulty.VERY_EASY,
                    question = "Find the missing value to balance the scale.",
                    equations = listOf("${12 + shift} + ? = ${25 + shift}"),
                    correctAnswer = 13,
                    hint1 = "Subtract the known number from the total.",
                    hint2 = "${25 + shift} - ${12 + shift} = 13.",
                    hint3 = "13.",
                    explanation = "${12 + shift} + 13 = ${25 + shift}."
                )
            ),
            DailyPuzzle(
                dayIndex = 2,
                levelData = LevelData(
                    id = 102,
                    title = "Daily 2: Step Count",
                    type = PuzzleType.NUMBER_PATTERN,
                    difficulty = Difficulty.VERY_EASY,
                    question = "What number comes next in this sequence?",
                    equations = listOf("${4 + shift} → ${8 + shift} → ${12 + shift} → ${16 + shift} → ?"),
                    correctAnswer = 20 + shift,
                    hint1 = "Each number increases by 4.",
                    hint2 = "Add 4 to the last number.",
                    hint3 = "${16 + shift} + 4 = ${20 + shift}.",
                    explanation = "The sequence adds 4 at each step: ${20 + shift}."
                )
            ),
            DailyPuzzle(
                dayIndex = 3,
                levelData = LevelData(
                    id = 103,
                    title = "Daily 3: Corner Sum",
                    type = PuzzleType.GEOMETRIC_TRIANGLE,
                    difficulty = Difficulty.EASY,
                    question = "Center equals the sum of the three corners.",
                    shapeLabels = listOf("Apex: 6", "Left: 8", "Right: 7", "Center: ?"),
                    numbers = listOf(6, 8, 7),
                    correctAnswer = 21,
                    hint1 = "Add all three corner values.",
                    hint2 = "6 + 8 + 7.",
                    hint3 = "21.",
                    explanation = "6 + 8 + 7 = 21."
                )
            ),
            DailyPuzzle(
                dayIndex = 4,
                levelData = LevelData(
                    id = 104,
                    title = "Daily 4: Grid Balance",
                    type = PuzzleType.NUMBER_GRID,
                    difficulty = Difficulty.EASY,
                    question = "Each row adds up to 16.",
                    gridData = listOf(
                        listOf("9", "7"),
                        listOf("10", "?")
                    ),
                    correctAnswer = 6,
                    hint1 = "The row sum is 16.",
                    hint2 = "10 + ? = 16.",
                    hint3 = "16 - 10 = 6.",
                    explanation = "10 + 6 = 16."
                )
            ),
            DailyPuzzle(
                dayIndex = 5,
                levelData = LevelData(
                    id = 105,
                    title = "Daily 5: Twin Factor",
                    type = PuzzleType.RELATIONSHIP,
                    difficulty = Difficulty.MEDIUM,
                    question = "Find A and B where A > B.",
                    equations = listOf(
                        "A × B = 36",
                        "A + B = 13",
                        "A - B = ?"
                    ),
                    correctAnswer = 5,
                    hint1 = "Factors of 36 that sum to 13 are 9 and 4.",
                    hint2 = "A = 9, B = 4.",
                    hint3 = "9 - 4 = 5.",
                    explanation = "9 × 4 = 36 and 9 + 4 = 13. 9 - 4 = 5."
                )
            ),
            DailyPuzzle(
                dayIndex = 6,
                levelData = LevelData(
                    id = 106,
                    title = "Daily 6: Square Shift",
                    type = PuzzleType.NUMBER_PATTERN,
                    difficulty = Difficulty.MEDIUM,
                    question = "Discover the square number progression.",
                    equations = listOf("3 → 6 → 11 → 18 → ?"),
                    numbers = listOf(3, 6, 11, 18),
                    correctAnswer = 27,
                    hint1 = "Look at the differences: +3, +5, +7...",
                    hint2 = "Next difference is +9.",
                    hint3 = "18 + 9 = 27.",
                    explanation = "Consecutive odd differences: 18 + 9 = 27 (or n² + 2)."
                )
            ),
            DailyPuzzle(
                dayIndex = 7,
                levelData = LevelData(
                    id = 107,
                    title = "Daily 7: Sector Matrix",
                    type = PuzzleType.GEOMETRIC_CIRCLE,
                    difficulty = Difficulty.HARD,
                    question = "Opposite sectors multiply to 48.",
                    shapeLabels = listOf("Top: 6", "Bottom: 8", "Left: 4", "Right: ?"),
                    numbers = listOf(6, 8, 4),
                    correctAnswer = 12,
                    hint1 = "Top (6) × Bottom (8) = 48.",
                    hint2 = "Left (4) × Right = 48.",
                    hint3 = "48 / 4 = 12.",
                    explanation = "4 × 12 = 48."
                )
            ),
            DailyPuzzle(
                dayIndex = 8,
                levelData = LevelData(
                    id = 108,
                    title = "Daily 8: Matrix Mean",
                    type = PuzzleType.NUMBER_GRID,
                    difficulty = Difficulty.HARD,
                    question = "Col 1 and Col 2 average to Col 3.",
                    gridData = listOf(
                        listOf("10", "20", "15"),
                        listOf("14", "26", "20"),
                        listOf("18", "30", "?")
                    ),
                    correctAnswer = 24,
                    hint1 = "(Col 1 + Col 2) / 2 = Col 3.",
                    hint2 = "(18 + 30) / 2.",
                    hint3 = "48 / 2 = 24.",
                    explanation = "(18 + 30) / 2 = 24."
                )
            ),
            DailyPuzzle(
                dayIndex = 9,
                levelData = LevelData(
                    id = 109,
                    title = "Daily 9: Triple Symbol",
                    type = PuzzleType.MIXED_LOGIC,
                    difficulty = Difficulty.HARD,
                    question = "Solve the system using order of operations.",
                    equations = listOf(
                        "🔷 + 🔷 = 16",
                        "🔷 × 🔶 = 40",
                        "🔶 + 🔺 = 12",
                        "🔺 + 🔷 × 🔶 = ?"
                    ),
                    correctAnswer = 47,
                    hint1 = "🔷 = 8, 🔶 = 5, 🔺 = 7.",
                    hint2 = "Order of operations: multiply before adding!",
                    hint3 = "7 + (8 × 5) = 7 + 40 = 47.",
                    explanation = "🔷 = 8, 🔶 = 5, 🔺 = 7. 7 + (8 × 5) = 47."
                )
            ),
            DailyPuzzle(
                dayIndex = 10,
                levelData = LevelData(
                    id = 110,
                    title = "Daily 10: Grand Daily Challenge",
                    type = PuzzleType.RELATIONSHIP,
                    difficulty = Difficulty.EXPERT,
                    question = "Solve the system to find the sum A + B + C.",
                    equations = listOf(
                        "A + B = 14",
                        "B × C = 30",
                        "A × C = 40",
                        "A + B + C = ?"
                    ),
                    correctAnswer = 19,
                    hint1 = "Notice that (A × C) ÷ (B × C) = A ÷ B = 40 ÷ 30 = 4 ÷ 3.",
                    hint2 = "If A = 4k and B = 3k, then 4k + 3k = 7k = 14, meaning k = 2 (so A = 8, B = 6).",
                    hint3 = "Since A × C = 40, C = 5. Finally, 8 + 6 + 5 = 19.",
                    explanation = "A = 8, B = 6, C = 5. A + B + C = 8 + 6 + 5 = 19."
                )
            )
        )
    }
}
