package com.example.data.model

object MathCrossData {
    val levels: List<MathCrossLevel> = listOf(
        MathCrossLevel(
            id = 1,
            title = "Cross Addition",
            difficulty = Difficulty.VERY_EASY,
            gridRows = listOf(
                listOf("4", "+", "3", "=", "7"),
                listOf("+", "", "+", "", "+"),
                listOf("2", "+", "5", "=", "7"),
                listOf("=", "", "=", "", "="),
                listOf("6", "+", "8", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 14,
            hint1 = "Add across Row 5: 6 + 8.",
            hint2 = "Also verify down Column 5: 7 + 7.",
            explanation = "Both 6 + 8 = 14 and 7 + 7 = 14 confirm the answer is 14."
        ),
        MathCrossLevel(
            id = 2,
            title = "Subtraction Cross",
            difficulty = Difficulty.EASY,
            gridRows = listOf(
                listOf("15", "-", "6", "=", "9"),
                listOf("-", "", "-", "", "-"),
                listOf("7", "-", "2", "=", "5"),
                listOf("=", "", "=", "", "="),
                listOf("8", "-", "4", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 4,
            hint1 = "Look across Row 5: 8 - 4.",
            hint2 = "Check down Column 5: 9 - 5.",
            explanation = "Row 5: 8 - 4 = 4. Column 5: 9 - 5 = 4."
        ),
        MathCrossLevel(
            id = 3,
            title = "Multiplication Grid",
            difficulty = Difficulty.EASY,
            gridRows = listOf(
                listOf("2", "×", "4", "=", "8"),
                listOf("×", "", "×", "", "×"),
                listOf("3", "×", "2", "=", "6"),
                listOf("=", "", "=", "", "="),
                listOf("6", "×", "8", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 48,
            hint1 = "Row 5: 6 × 8.",
            hint2 = "Column 5: 8 × 6.",
            explanation = "6 × 8 = 48 and 8 × 6 = 48. Answer is 48."
        ),
        MathCrossLevel(
            id = 4,
            title = "Division & Add Cross",
            difficulty = Difficulty.MEDIUM,
            gridRows = listOf(
                listOf("20", "÷", "4", "=", "5"),
                listOf("+", "", "+", "", "+"),
                listOf("10", "÷", "2", "=", "5"),
                listOf("=", "", "=", "", "="),
                listOf("30", "÷", "6", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 5,
            hint1 = "Row 5: 30 ÷ 6.",
            hint2 = "Col 1 is 20 + 10 = 30, Col 3 is 4 + 2 = 6, Col 5 is 5 + 5 = 10? Wait, 30 ÷ 6 = 5.",
            explanation = "30 ÷ 6 = 5."
        ),
        MathCrossLevel(
            id = 5,
            title = "Interlocking Double",
            difficulty = Difficulty.MEDIUM,
            gridRows = listOf(
                listOf("12", "+", "9", "=", "21"),
                listOf("-", "", "-", "", "-"),
                listOf("4", "+", "3", "=", "7"),
                listOf("=", "", "=", "", "="),
                listOf("8", "+", "6", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 14,
            hint1 = "Row 5: 8 + 6 = 14.",
            hint2 = "Column 5: 21 - 7 = 14.",
            explanation = "8 + 6 = 14 and 21 - 7 = 14."
        ),
        MathCrossLevel(
            id = 6,
            title = "Triple Multiply",
            difficulty = Difficulty.HARD,
            gridRows = listOf(
                listOf("5", "×", "3", "=", "15"),
                listOf("×", "", "×", "", "×"),
                listOf("4", "×", "2", "=", "8"),
                listOf("=", "", "=", "", "="),
                listOf("20", "×", "6", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 120,
            hint1 = "Row 5: 20 × 6.",
            hint2 = "Column 5: 15 × 8.",
            explanation = "20 × 6 = 120 and 15 × 8 = 120."
        ),
        MathCrossLevel(
            id = 7,
            title = "Mixed Arithmetic Cross",
            difficulty = Difficulty.HARD,
            gridRows = listOf(
                listOf("18", "+", "14", "=", "32"),
                listOf("-", "", "-", "", "-"),
                listOf("6", "+", "5", "=", "11"),
                listOf("=", "", "=", "", "="),
                listOf("12", "+", "9", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 21,
            hint1 = "Row 5: 12 + 9.",
            hint2 = "Column 5: 32 - 11.",
            explanation = "12 + 9 = 21 and 32 - 11 = 21."
        ),
        MathCrossLevel(
            id = 8,
            title = "Grand Master Cross",
            difficulty = Difficulty.EXPERT,
            gridRows = listOf(
                listOf("36", "+", "24", "=", "60"),
                listOf("-", "", "-", "", "-"),
                listOf("14", "+", "9", "=", "23"),
                listOf("=", "", "=", "", "="),
                listOf("22", "+", "15", "=", "?")
            ),
            targetRow = 4,
            targetCol = 4,
            correctAnswer = 37,
            hint1 = "Row 5: 22 + 15.",
            hint2 = "Column 5: 60 - 23.",
            explanation = "22 + 15 = 37 and 60 - 23 = 37. Perfect cross match!"
        )
    )
}
