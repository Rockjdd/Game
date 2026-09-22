package com.example.data.model

object AllLevelsData {
    val levels: List<LevelData> = listOf(
        // Level 1: Basic Missing Number / Addition
        LevelData(
            id = 1,
            title = "Missing Sum",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.VERY_EASY,
            question = "Find the missing number to complete the equation.",
            equations = listOf("7 + ? = 15"),
            correctAnswer = 8,
            hint1 = "Think about what number added to 7 gives 15.",
            hint2 = "Subtract 7 from 15 to find the unknown value.",
            hint3 = "15 - 7 = 8.",
            explanation = "7 + 8 = 15. The missing number is 8."
        ),

        // Level 2: Simple Sequence Pattern (+3)
        LevelData(
            id = 2,
            title = "Counting Steps",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.VERY_EASY,
            question = "Discover the pattern and determine the next number in the sequence.",
            equations = listOf("3 → 6 → 9 → 12 → ?"),
            numbers = listOf(3, 6, 9, 12),
            correctAnswer = 15,
            hint1 = "Look at how much the number grows at each step.",
            hint2 = "Each number increases by adding 3.",
            hint3 = "12 + 3 = 15.",
            explanation = "The sequence adds 3 every step: 3, 6, 9, 12, 15."
        ),

        // Level 3: Geometric Triangle (Corner Sum)
        LevelData(
            id = 3,
            title = "Triangle Harmony",
            type = PuzzleType.GEOMETRIC_TRIANGLE,
            difficulty = Difficulty.VERY_EASY,
            question = "The number in the center is formed by the three corner numbers.",
            shapeLabels = listOf("Top: 4", "Left: 5", "Right: 6", "Center: ?"),
            numbers = listOf(4, 5, 6), // Center is ?
            correctAnswer = 15,
            hint1 = "Look at the numbers at the three corners of the triangle.",
            hint2 = "Add all three corner values together.",
            hint3 = "4 + 5 + 6 = 15.",
            explanation = "The center is the sum of the corners: 4 + 5 + 6 = 15."
        ),

        // Level 4: Two Variables Linear
        LevelData(
            id = 4,
            title = "Twin Symbols",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.VERY_EASY,
            question = "Calculate the values of A and B to find B.",
            equations = listOf(
                "A + A = 20",
                "A + B = 17",
                "B = ?"
            ),
            correctAnswer = 7,
            hint1 = "Start with the first equation: two identical numbers add up to 20.",
            hint2 = "Since A + A = 20, A must be 10.",
            hint3 = "Substitute A = 10 into the second line: 10 + B = 17.",
            explanation = "A + A = 20 implies A = 10. Then 10 + B = 17 gives B = 7."
        ),

        // Level 5: 2x2 Number Grid
        LevelData(
            id = 5,
            title = "Balanced Grid",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.VERY_EASY,
            question = "Each row in this grid follows the same mathematical sum.",
            gridData = listOf(
                listOf("5", "4"),
                listOf("3", "?")
            ),
            correctAnswer = 6,
            hint1 = "Find the sum of the top row first: 5 + 4.",
            hint2 = "The top row sums to 9. The bottom row must also sum to 9.",
            hint3 = "3 + ? = 9.",
            explanation = "Each row sums to 9: 5 + 4 = 9, and 3 + 6 = 9. So the answer is 6."
        ),

        // Level 6: Number Pattern (-4)
        LevelData(
            id = 6,
            title = "Stepping Down",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.VERY_EASY,
            question = "Identify the rule of the sequence to find the missing value.",
            equations = listOf("30 → 26 → 22 → 18 → ?"),
            numbers = listOf(30, 26, 22, 18),
            correctAnswer = 14,
            hint1 = "Notice that the numbers are decreasing steadily.",
            hint2 = "Calculate the difference: 30 - 26 = 4. Each step subtracts 4.",
            hint3 = "18 - 4 = 14.",
            explanation = "Each number decreases by 4: 18 - 4 = 14."
        ),

        // Level 7: Fruit Balance System
        LevelData(
            id = 7,
            title = "Fruit Balance",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.VERY_EASY,
            question = "Find the value of each fruit to calculate Orange - Apple.",
            equations = listOf(
                "🍎 + 🍎 + 🍎 = 12",
                "🍎 + 🍊 = 10",
                "🍊 - 🍎 = ?"
            ),
            correctAnswer = 2,
            hint1 = "Find the value of one Apple (🍎) first.",
            hint2 = "3 Apples = 12 means Apple = 4. Then 4 + Orange = 10, so Orange = 6.",
            hint3 = "Orange - Apple = 6 - 4 = 2.",
            explanation = "Apple = 4, Orange = 6. Therefore, Orange - Apple = 6 - 4 = 2."
        ),

        // Level 8: Circle Opposite Sectors
        LevelData(
            id = 8,
            title = "Circle Symmetry",
            type = PuzzleType.GEOMETRIC_CIRCLE,
            difficulty = Difficulty.VERY_EASY,
            question = "Opposite sectors across the center share a constant mathematical relation.",
            shapeLabels = listOf("Top: 3", "Bottom: 7", "Left: 4", "Right: ?"),
            numbers = listOf(3, 7, 4),
            correctAnswer = 6,
            hint1 = "Check the sum of opposite numbers: Top (3) + Bottom (7).",
            hint2 = "Opposite numbers add up to 10 (3 + 7 = 10).",
            hint3 = "4 + ? = 10.",
            explanation = "Opposite numbers sum to 10. 4 + 6 = 10, so the answer is 6."
        ),

        // Level 9: Doubling Pattern
        LevelData(
            id = 9,
            title = "Exponential Leap",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.VERY_EASY,
            question = "What number comes next in this multiplying pattern?",
            equations = listOf("2 → 4 → 8 → 16 → ?"),
            numbers = listOf(2, 4, 8, 16),
            correctAnswer = 32,
            hint1 = "Compare consecutive numbers using multiplication.",
            hint2 = "Each number is multiplied by 2 (doubled).",
            hint3 = "16 × 2 = 32.",
            explanation = "Every term doubles: 2, 4, 8, 16, 32."
        ),

        // Level 10: Milestone 1! Square Corners to Center
        LevelData(
            id = 10,
            title = "Milestone: Corner Vault",
            type = PuzzleType.GEOMETRIC_DIAMOND,
            difficulty = Difficulty.VERY_EASY,
            question = "The 4 corner numbers combine to create the center value.",
            equations = listOf(
                "Example: Corners [2, 3, 5, 8] → Center: 18",
                "Puzzle: Corners [4, 7, 3, 6] → Center: ?"
            ),
            shapeLabels = listOf("Top-Left: 4", "Top-Right: 7", "Bottom-Left: 3", "Bottom-Right: 6"),
            numbers = listOf(4, 7, 3, 6),
            correctAnswer = 20,
            hint1 = "Examine the example: 2 + 3 + 5 + 8 = 18.",
            hint2 = "Sum all 4 corner numbers of the current shape.",
            hint3 = "4 + 7 + 3 + 6 = 20.",
            explanation = "Center is the sum of the four outer numbers: 4 + 7 + 3 + 6 = 20."
        ),

        // Level 11: Multiplication & Difference System
        LevelData(
            id = 11,
            title = "Factor Search",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.EASY,
            question = "Find two numbers A and B where A > B.",
            equations = listOf(
                "A × B = 24",
                "A + B = 11",
                "A - B = ?"
            ),
            correctAnswer = 5,
            hint1 = "Think about pairs of factors of 24.",
            hint2 = "8 and 3 multiply to 24 and sum to 11 (8 + 3 = 11).",
            hint3 = "Subtract: 8 - 3 = 5.",
            explanation = "A = 8 and B = 3 since 8 × 3 = 24 and 8 + 3 = 11. Therefore, 8 - 3 = 5."
        ),

        // Level 12: Increasing Difference Pattern
        LevelData(
            id = 12,
            title = "Growing Steps",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.EASY,
            question = "Find the missing number in this progressive sequence.",
            equations = listOf("5 → 7 → 11 → 17 → ?"),
            numbers = listOf(5, 7, 11, 17),
            correctAnswer = 25,
            hint1 = "Look at the differences between successive numbers: +2, +4, +6...",
            hint2 = "The difference increases by 2 each time. The next jump is +8.",
            hint3 = "17 + 8 = 25.",
            explanation = "Differences are +2, +4, +6, +8: 17 + 8 = 25."
        ),

        // Level 13: Geometric Triangle (Base Product + Apex)
        LevelData(
            id = 13,
            title = "Apex Fusion",
            type = PuzzleType.GEOMETRIC_TRIANGLE,
            difficulty = Difficulty.EASY,
            question = "Discover the formula connecting the corners to the center.",
            equations = listOf(
                "Triangle A: [Top: 2, Left: 3, Right: 4] → Center: 14",
                "Triangle B: [Top: 4, Left: 7, Right: 3] → Center: ?"
            ),
            shapeLabels = listOf("Apex: 4", "Left: 7", "Right: 3", "Center: ?"),
            numbers = listOf(4, 7, 3),
            correctAnswer = 25,
            hint1 = "In Triangle A: (3 × 4) + 2 = 12 + 2 = 14.",
            hint2 = "Multiply the two bottom numbers, then add the top number.",
            hint3 = "(7 × 3) + 4 = 21 + 4 = 25.",
            explanation = "(Left × Right) + Top: (7 × 3) + 4 = 21 + 4 = 25."
        ),

        // Level 14: 3x3 Grid Multiplication Row
        LevelData(
            id = 14,
            title = "Product Matrix",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.EASY,
            question = "Each row obeys the same mathematical operation.",
            gridData = listOf(
                listOf("2", "3", "6"),
                listOf("4", "5", "20"),
                listOf("7", "6", "?")
            ),
            correctAnswer = 42,
            hint1 = "Look at each row from left to right: 2, 3 gives 6.",
            hint2 = "Column 1 × Column 2 = Column 3.",
            hint3 = "7 × 6 = 42.",
            explanation = "Each row is Col 1 × Col 2 = Col 3: 7 × 6 = 42."
        ),

        // Level 15: Simultaneous Linear Equations
        LevelData(
            id = 15,
            title = "Dual Balance",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.EASY,
            question = "Solve for A and B to find their product.",
            equations = listOf(
                "A + B = 30",
                "A - B = 14",
                "A × B = ?"
            ),
            correctAnswer = 176,
            hint1 = "Add both equations: (A + B) + (A - B) = 30 + 14.",
            hint2 = "2A = 44, so A = 22. Then B = 30 - 22 = 8.",
            hint3 = "Multiply: 22 × 8 = 176.",
            explanation = "A = 22 and B = 8. Their product is 22 × 8 = 176."
        ),

        // Level 16: Diamond Cross Product
        LevelData(
            id = 16,
            title = "Diamond Cross",
            type = PuzzleType.GEOMETRIC_DIAMOND,
            difficulty = Difficulty.EASY,
            question = "Vertical and horizontal opposite pairs have equal products.",
            shapeLabels = listOf("Top: 3", "Bottom: 8", "Left: 4", "Right: ?"),
            numbers = listOf(3, 8, 4),
            correctAnswer = 6,
            hint1 = "Multiply the vertical pair: Top (3) × Bottom (8) = 24.",
            hint2 = "The horizontal pair must multiply to the same product (24).",
            hint3 = "4 × ? = 24.",
            explanation = "Top × Bottom = Left × Right: 3 × 8 = 24, so 4 × 6 = 24."
        ),

        // Level 17: Pattern (2n + 1)
        LevelData(
            id = 17,
            title = "Double Plus One",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.EASY,
            question = "What number follows this sequence rule?",
            equations = listOf("3 → 7 → 15 → 31 → ?"),
            numbers = listOf(3, 7, 15, 31),
            correctAnswer = 63,
            hint1 = "Notice that each number is slightly more than double the previous.",
            hint2 = "The rule is: (Number × 2) + 1.",
            hint3 = "(31 × 2) + 1 = 62 + 1 = 63.",
            explanation = "(31 × 2) + 1 = 63. Each step is (n × 2) + 1."
        ),

        // Level 18: Math Cross Mini
        LevelData(
            id = 18,
            title = "Cross Intersection",
            type = PuzzleType.MATH_CROSS,
            difficulty = Difficulty.EASY,
            question = "All rows and columns must be mathematically valid.",
            gridData = listOf(
                listOf("6", "+", "4", "=", "10"),
                listOf("-", "", "-", "", "-"),
                listOf("2", "+", "3", "=", "5"),
                listOf("=", "", "=", "", "="),
                listOf("4", "+", "1", "=", "?")
            ),
            correctAnswer = 5,
            hint1 = "Calculate Row 5: 4 + 1.",
            hint2 = "Also check Column 5: 10 - 5.",
            hint3 = "Both directions give 5.",
            explanation = "Row 5: 4 + 1 = 5. Column 5: 10 - 5 = 5. The answer is 5."
        ),

        // Level 19: Hexagon Opposite Differences
        LevelData(
            id = 19,
            title = "Hexagon Delta",
            type = PuzzleType.GEOMETRIC_HEXAGON,
            difficulty = Difficulty.EASY,
            question = "Numbers on opposite vertices have an identical difference.",
            shapeLabels = listOf("Pair 1: (8, 2)", "Pair 2: (9, 3)", "Pair 3: (11, ?)"),
            numbers = listOf(8, 2, 9, 3, 11),
            correctAnswer = 5,
            hint1 = "Look at the differences: 8 - 2 = 6, and 9 - 3 = 6.",
            hint2 = "The difference for every opposite pair is 6.",
            hint3 = "11 - ? = 6.",
            explanation = "11 - 5 = 6. The constant difference is 6."
        ),

        // Level 20: Milestone 2! Multi-step Symbol Order of Operations
        LevelData(
            id = 20,
            title = "Milestone: Order of Stars",
            type = PuzzleType.MIXED_LOGIC,
            difficulty = Difficulty.EASY,
            question = "Determine the value of each symbol. Remember order of operations!",
            equations = listOf(
                "⭐ + ⭐ + ⭐ = 30",
                "⭐ + 🌙 + 🌙 = 20",
                "🌙 + 💎 + 💎 = 9",
                "💎 + ⭐ × 🌙 = ?"
            ),
            correctAnswer = 52,
            hint1 = "3 Stars = 30 (Star = 10). 10 + 2 Moons = 20 (Moon = 5).",
            hint2 = "5 + 2 Gems = 9 (Gem = 2).",
            hint3 = "Multiply first: Gem + (Star × Moon) = 2 + (10 × 5) = 2 + 50 = 52.",
            explanation = "Star = 10, Moon = 5, Gem = 2. Gem + (Star × Moon) = 2 + 50 = 52."
        ),

        // Level 21: 3x3 Magic Square Sum 15
        LevelData(
            id = 21,
            title = "Magic Square",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.MEDIUM,
            question = "Every row and column in this grid sums to the same total.",
            gridData = listOf(
                listOf("8", "1", "6"),
                listOf("3", "5", "7"),
                listOf("4", "?", "2")
            ),
            correctAnswer = 9,
            hint1 = "Find the constant sum: 8 + 1 + 6 = 15.",
            hint2 = "Row 3 must sum to 15: 4 + ? + 2 = 15.",
            hint3 = "15 - (4 + 2) = 15 - 6 = 9.",
            explanation = "Every row and column sums to 15: 4 + 9 + 2 = 15."
        ),

        // Level 22: Squares + 1 Pattern
        LevelData(
            id = 22,
            title = "Square Plus One",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.MEDIUM,
            question = "Find the logic behind this growing sequence.",
            equations = listOf("2 → 5 → 10 → 17 → 26 → ?"),
            numbers = listOf(2, 5, 10, 17, 26),
            correctAnswer = 37,
            hint1 = "Calculate the differences: +3, +5, +7, +9...",
            hint2 = "Notice that each term is n² + 1: 1²+1, 2²+1, 3²+1, 4²+1, 5²+1.",
            hint3 = "Next is 6² + 1 = 36 + 1 = 37 (or 26 + 11 = 37).",
            explanation = "Consecutive odd differences (+3, +5, +7, +9, +11) gives 26 + 11 = 37."
        ),

        // Level 23: 4-Quadrant Circle (Top Product - Bottom Product)
        LevelData(
            id = 23,
            title = "Quadrant Net",
            type = PuzzleType.GEOMETRIC_CIRCLE,
            difficulty = Difficulty.MEDIUM,
            question = "Discover how the 4 sectors compute the center number.",
            equations = listOf(
                "Circle A: [TL: 5, TR: 4, BL: 3, BR: 2] → Center: 14",
                "Circle B: [TL: 7, TR: 4, BL: 5, BR: 2] → Center: ?"
            ),
            shapeLabels = listOf("TL: 7", "TR: 4", "BL: 5", "BR: 2", "Center: ?"),
            numbers = listOf(7, 4, 5, 2),
            correctAnswer = 18,
            hint1 = "In Circle A: (5 × 4) - (3 × 2) = 20 - 6 = 14.",
            hint2 = "(TopLeft × TopRight) - (BottomLeft × BottomRight).",
            hint3 = "(7 × 4) - (5 × 2) = 28 - 10 = 18.",
            explanation = "(7 × 4) - (5 × 2) = 28 - 10 = 18."
        ),

        // Level 24: Three Variable Linear System
        LevelData(
            id = 24,
            title = "Triple Variable",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.MEDIUM,
            question = "Solve the system of equations to determine B.",
            equations = listOf(
                "A + B + C = 24",
                "A + B = 15",
                "B + C = 17",
                "B = ?"
            ),
            correctAnswer = 8,
            hint1 = "Substitute A + B = 15 into the first equation to find C.",
            hint2 = "15 + C = 24 means C = 9.",
            hint3 = "Substitute C = 9 into B + C = 17: B = 17 - 9 = 8.",
            explanation = "C = 24 - 15 = 9. Then B = 17 - 9 = 8."
        ),

        // Level 25: Grid (Col 1² + Col 2 = Col 3)
        LevelData(
            id = 25,
            title = "Square and Add",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.MEDIUM,
            question = "Identify the row relationship between the columns.",
            gridData = listOf(
                listOf("3", "4", "13"),
                listOf("4", "5", "21"),
                listOf("5", "6", "?")
            ),
            correctAnswer = 31,
            hint1 = "Row 1: 3² + 4 = 9 + 4 = 13.",
            hint2 = "Square Column 1 and add Column 2.",
            hint3 = "5² + 6 = 25 + 6 = 31.",
            explanation = "(Col 1)² + Col 2 = Col 3: 5² + 6 = 25 + 6 = 31."
        ),

        // Level 26: Fibonacci Sequence
        LevelData(
            id = 26,
            title = "Fibonacci Flow",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.MEDIUM,
            question = "Each term relates to its two predecessors.",
            equations = listOf("2 → 3 → 5 → 8 → 13 → 21 → ?"),
            numbers = listOf(2, 3, 5, 8, 13, 21),
            correctAnswer = 34,
            hint1 = "Add any two consecutive numbers together: 2 + 3 = 5, 3 + 5 = 8.",
            hint2 = "Each number is the sum of the two preceding numbers.",
            hint3 = "13 + 21 = 34.",
            explanation = "Fibonacci rule: 13 + 21 = 34."
        ),

        // Level 27: Triangle ((Top + Left) × Right)
        LevelData(
            id = 27,
            title = "Sum and Scale",
            type = PuzzleType.GEOMETRIC_TRIANGLE,
            difficulty = Difficulty.MEDIUM,
            question = "Corner numbers combine to form the center value.",
            equations = listOf(
                "Triangle 1: [Top: 2, Left: 3, Right: 4] → Center: 20",
                "Triangle 2: [Top: 4, Left: 1, Right: 5] → Center: 25",
                "Triangle 3: [Top: 3, Left: 5, Right: 6] → Center: ?"
            ),
            shapeLabels = listOf("Top: 3", "Left: 5", "Right: 6", "Center: ?"),
            numbers = listOf(3, 5, 6),
            correctAnswer = 48,
            hint1 = "In Triangle 1: (2 + 3) × 4 = 5 × 4 = 20.",
            hint2 = "(Top + Left) × Right.",
            hint3 = "(3 + 5) × 6 = 8 × 6 = 48.",
            explanation = "(Top + Left) × Right = (3 + 5) × 6 = 48."
        ),

        // Level 28: Math Cross Product Grid
        LevelData(
            id = 28,
            title = "Product Cross",
            type = PuzzleType.MATH_CROSS,
            difficulty = Difficulty.MEDIUM,
            question = "Solve the crossword grid where all operations are multiplication.",
            gridData = listOf(
                listOf("3", "×", "4", "=", "12"),
                listOf("×", "", "×", "", "×"),
                listOf("2", "×", "5", "=", "10"),
                listOf("=", "", "=", "", "="),
                listOf("6", "×", "20", "=", "?")
            ),
            correctAnswer = 120,
            hint1 = "Calculate Row 5: 6 × 20.",
            hint2 = "Verify with Column 5: 12 × 10.",
            hint3 = "Both equal 120.",
            explanation = "6 × 20 = 120 and 12 × 10 = 120. The answer is 120."
        ),

        // Level 29: Ratio and Difference
        LevelData(
            id = 29,
            title = "Ratio Harmony",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.MEDIUM,
            question = "Solve for A and B to find their difference.",
            equations = listOf(
                "A ÷ B = 4",
                "A + B = 45",
                "A - B = ?"
            ),
            correctAnswer = 27,
            hint1 = "A ÷ B = 4 means A = 4B.",
            hint2 = "Substitute A = 4B: 4B + B = 5B = 45, so B = 9 and A = 36.",
            hint3 = "A - B = 36 - 9 = 27.",
            explanation = "A = 36, B = 9. A - B = 36 - 9 = 27."
        ),

        // Level 30: Milestone 3! Concentric Squares
        LevelData(
            id = 30,
            title = "Milestone: Ring of Squares",
            type = PuzzleType.GEOMETRIC_CIRCLE,
            difficulty = Difficulty.MEDIUM,
            question = "Each inner value corresponds to its outer counterpart.",
            shapeLabels = listOf("Outer: [3, 4, 5, 6]", "Inner: [9, 16, 25, ?]"),
            numbers = listOf(3, 4, 5, 6),
            correctAnswer = 36,
            hint1 = "Compare outer numbers to inner numbers: 3 → 9, 4 → 16, 5 → 25.",
            hint2 = "Each inner number is the square of the outer number.",
            hint3 = "6² = 36.",
            explanation = "Inner = (Outer)²: 6² = 36."
        ),

        // Level 31: 3x3 Grid Mean / Average
        LevelData(
            id = 31,
            title = "Mean Matrix",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.HARD,
            question = "Find the relationship between the rows of this grid.",
            gridData = listOf(
                listOf("12", "14", "16"),
                listOf("8", "10", "12"),
                listOf("10", "12", "?")
            ),
            correctAnswer = 14,
            hint1 = "Compare Row 3 with the two numbers directly above it.",
            hint2 = "Row 3 is the average of Row 1 and Row 2: (Row 1 + Row 2) / 2.",
            hint3 = "(16 + 12) / 2 = 28 / 2 = 14.",
            explanation = "Each element in Row 3 is the mean of the two above it: (16 + 12) / 2 = 14."
        ),

        // Level 32: Alternating Operations (×3, -2)
        LevelData(
            id = 32,
            title = "Pendulum Math",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.HARD,
            question = "What number continues this dual-operation sequence?",
            equations = listOf("2 → 6 → 4 → 12 → 10 → 30 → ?"),
            numbers = listOf(2, 6, 4, 12, 10, 30),
            correctAnswer = 28,
            hint1 = "Notice that the sequence alternates between multiplying and subtracting.",
            hint2 = "Step 1: ×3 (2×3=6). Step 2: -2 (6-2=4). Step 3: ×3 (4×3=12)...",
            hint3 = "After multiplying by 3 to reach 30, the next step is subtracting 2: 30 - 2 = 28.",
            explanation = "The pattern alternates ×3 and -2: 30 - 2 = 28."
        ),

        // Level 33: Hexagon Opposite Differences Sum to Center
        LevelData(
            id = 33,
            title = "Hexagon Core",
            type = PuzzleType.GEOMETRIC_HEXAGON,
            difficulty = Difficulty.HARD,
            question = "Sum the differences of opposite vertex pairs to find the center.",
            shapeLabels = listOf(
                "Opposite Pairs: (20, 12), (25, 15), (17, 8)",
                "Center: ?"
            ),
            numbers = listOf(20, 12, 25, 15, 17, 8),
            correctAnswer = 27,
            hint1 = "Calculate the difference between each of the three opposite pairs.",
            hint2 = "20 - 12 = 8; 25 - 15 = 10; 17 - 8 = 9.",
            hint3 = "Add the three differences: 8 + 10 + 9 = 27.",
            explanation = "(20 - 12) + (25 - 15) + (17 - 8) = 8 + 10 + 9 = 27."
        ),

        // Level 34: Non-linear Quadratic System
        LevelData(
            id = 34,
            title = "Quadratic Twins",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.HARD,
            question = "Find positive whole numbers X and Y that satisfy both equations.",
            equations = listOf(
                "X² + Y = 41",
                "Y² + X = 31",
                "X × Y = ?"
            ),
            correctAnswer = 30,
            hint1 = "Since X² is close to 41, test small integers for X (e.g., 6).",
            hint2 = "If X = 6: 6² + Y = 36 + Y = 41 implies Y = 5.",
            hint3 = "Check: 5² + 6 = 25 + 6 = 31. Then X × Y = 6 × 5 = 30.",
            explanation = "X = 6 and Y = 5 satisfy both equations. 6 × 5 = 30."
        ),

        // Level 35: Grid Product Minus One
        LevelData(
            id = 35,
            title = "Minus One Matrix",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.HARD,
            question = "Find the column rule to determine the missing number.",
            gridData = listOf(
                listOf("3", "5", "7"),
                listOf("4", "6", "8"),
                listOf("11", "29", "?")
            ),
            correctAnswer = 55,
            hint1 = "Column 1: 3 × 4 = 12, and 12 - 1 = 11.",
            hint2 = "In each column: (Row 1 × Row 2) - 1 = Row 3.",
            hint3 = "(7 × 8) - 1 = 56 - 1 = 55.",
            explanation = "(7 × 8) - 1 = 55."
        ),

        // Level 36: Cubic Increment Pattern
        LevelData(
            id = 36,
            title = "Cubic Ascension",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.HARD,
            question = "Uncover the higher-order mathematical pattern.",
            equations = listOf("1 → 2 → 10 → 37 → ?"),
            numbers = listOf(1, 2, 10, 37),
            correctAnswer = 101,
            hint1 = "Find the step differences: 2-1 = 1, 10-2 = 8, 37-10 = 27.",
            hint2 = "The differences are perfect cubes: 1³, 2³ (8), 3³ (27)...",
            hint3 = "Next difference is 4³ = 64: 37 + 64 = 101.",
            explanation = "Differences are cubes of integers: 37 + 4³ = 37 + 64 = 101."
        ),

        // Level 37: Geometric Cross Arm Calculation
        LevelData(
            id = 37,
            title = "Cross Product Arms",
            type = PuzzleType.GEOMETRIC_CROSS,
            difficulty = Difficulty.HARD,
            question = "The center equals (Top + Bottom) × (Left - Right).",
            shapeLabels = listOf("Top: 8", "Bottom: 4", "Left: 9", "Right: 4", "Center: ?"),
            numbers = listOf(8, 4, 9, 4),
            correctAnswer = 60,
            hint1 = "Sum the vertical arms: 8 + 4 = 12.",
            hint2 = "Subtract the horizontal arms: 9 - 4 = 5.",
            hint3 = "Multiply the two results: 12 × 5 = 60.",
            explanation = "(8 + 4) × (9 - 4) = 12 × 5 = 60."
        ),

        // Level 38: Math Cross Mixed Logic
        LevelData(
            id = 38,
            title = "Dual Equation Cross",
            type = PuzzleType.MATH_CROSS,
            difficulty = Difficulty.HARD,
            question = "Complete the crossword math grid.",
            gridData = listOf(
                listOf("24", "+", "18", "=", "42"),
                listOf("-", "", "-", "", "-"),
                listOf("9", "+", "7", "=", "16"),
                listOf("=", "", "=", "", "="),
                listOf("15", "+", "11", "=", "?")
            ),
            correctAnswer = 26,
            hint1 = "Calculate Row 5: 15 + 11.",
            hint2 = "Check Column 5: 42 - 16.",
            hint3 = "Both calculate to 26.",
            explanation = "15 + 11 = 26 and 42 - 16 = 26. The answer is 26."
        ),

        // Level 39: Cyclic Product System
        LevelData(
            id = 39,
            title = "Cyclic Factors",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.HARD,
            question = "Find the positive integers A, B, and C to compute A + B + C.",
            equations = listOf(
                "A × B = 48",
                "B × C = 72",
                "A × C = 54",
                "A + B + C = ?"
            ),
            correctAnswer = 23,
            hint1 = "Multiply all three equations: (A × B × C)² = 48 × 72 × 54.",
            hint2 = "Solving gives A = 6, B = 8, and C = 9.",
            hint3 = "6 + 8 + 9 = 23.",
            explanation = "A = 6, B = 8, C = 9. Sum = 6 + 8 + 9 = 23."
        ),

        // Level 40: Milestone 4! 5-Pointed Star Vertex Law
        LevelData(
            id = 40,
            title = "Milestone: Star Vertex",
            type = PuzzleType.MIXED_LOGIC,
            difficulty = Difficulty.HARD,
            question = "Each inner vertex between two points A and B is (A × B) - (A + B).",
            equations = listOf(
                "Points 3 and 5 → Inner: (3 × 5) - (3 + 5) = 7",
                "Points 5 and 7 → Inner: (5 × 7) - (5 + 7) = 23",
                "Points 11 and 3 → Inner: ?"
            ),
            correctAnswer = 19,
            hint1 = "Apply the formula: (A × B) - (A + B).",
            hint2 = "Here A = 11 and B = 3.",
            hint3 = "(11 × 3) - (11 + 3) = 33 - 14 = 19.",
            explanation = "(11 × 3) - (11 + 3) = 33 - 14 = 19."
        ),

        // Level 41: Interleaved Two-Tier Sequence
        LevelData(
            id = 41,
            title = "Interleaved Strands",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.EXPERT,
            question = "Find the missing number in this dual interleaved sequence.",
            equations = listOf("4, 18, 8, 14, 16, 10, 32, ?"),
            numbers = listOf(4, 18, 8, 14, 16, 10, 32),
            correctAnswer = 6,
            hint1 = "Separate into two interleaved sequences: odd terms and even terms.",
            hint2 = "Odd positions double: 4, 8, 16, 32. Even positions decrease: 18, 14, 10...",
            hint3 = "Next is an even position: 10 - 4 = 6.",
            explanation = "The even sequence decreases by 4 each time: 18, 14, 10, 6."
        ),

        // Level 42: Row Sum Doubler Grid
        LevelData(
            id = 42,
            title = "Double Sum Grid",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.EXPERT,
            question = "Find the missing value in this four-row grid.",
            gridData = listOf(
                listOf("3", "5", "16"),
                listOf("4", "6", "20"),
                listOf("7", "8", "30"),
                listOf("9", "12", "?")
            ),
            correctAnswer = 42,
            hint1 = "Row 1: (3 + 5) × 2 = 8 × 2 = 16.",
            hint2 = "Each row satisfies (Col 1 + Col 2) × 2 = Col 3.",
            hint3 = "(9 + 12) × 2 = 21 × 2 = 42.",
            explanation = "(Col 1 + Col 2) × 2: (9 + 12) × 2 = 42."
        ),

        // Level 43: Digit Sum Multiplier
        LevelData(
            id = 43,
            title = "Digit Alchemy",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.EXPERT,
            question = "Decode the operator rule mapping inputs to outputs.",
            equations = listOf(
                "12 → 9",
                "23 → 15",
                "34 → 21",
                "48 → ?"
            ),
            correctAnswer = 36,
            hint1 = "Add the digits of each number: for 12, 1 + 2 = 3.",
            hint2 = "Multiply the digit sum by 3: 3 × 3 = 9. For 23: (2+3)×3 = 15.",
            hint3 = "For 48: (4 + 8) × 3 = 12 × 3 = 36.",
            explanation = "Sum of digits multiplied by 3: (4 + 8) × 3 = 12 × 3 = 36."
        ),

        // Level 44: Centroid Triangle
        LevelData(
            id = 44,
            title = "Centroid Mystery",
            type = PuzzleType.GEOMETRIC_TRIANGLE,
            difficulty = Difficulty.EXPERT,
            question = "Find the center number from the three outer vertex numbers.",
            shapeLabels = listOf("Corner A: 16", "Corner B: 28", "Corner C: 46", "Center: ?"),
            numbers = listOf(16, 28, 46),
            correctAnswer = 30,
            hint1 = "Look at the average (mean) of all three corners.",
            hint2 = "Add all three numbers: 16 + 28 + 46 = 90.",
            hint3 = "Divide by 3: 90 / 3 = 30.",
            explanation = "The center is the average of the three corners: (16 + 28 + 46) / 3 = 90 / 3 = 30."
        ),

        // Level 45: Powers of 2 and 3 System
        LevelData(
            id = 45,
            title = "Power Symphony",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.EXPERT,
            question = "Find whole numbers A, B, and C where A > B.",
            equations = listOf(
                "2ᴬ + 2ᴮ = 40",
                "3ᴮ + 3ᶜ = 36",
                "A × B × C = ?"
            ),
            correctAnswer = 30,
            hint1 = "Express 40 as the sum of two powers of 2 (32 + 8).",
            hint2 = "2⁵ = 32 and 2³ = 8, so A = 5 and B = 3.",
            hint3 = "3³ + 3ᶜ = 27 + 3ᶜ = 36 implies 3ᶜ = 9 (C = 2). Thus 5 × 3 × 2 = 30.",
            explanation = "A = 5, B = 3, C = 2. A × B × C = 5 × 3 × 2 = 30."
        ),

        // Level 46: Sequence (n³ - n)
        LevelData(
            id = 46,
            title = "Prismatic Formula",
            type = PuzzleType.NUMBER_PATTERN,
            difficulty = Difficulty.EXPERT,
            question = "Find the 6th number in this profound sequence.",
            equations = listOf("0 → 6 → 24 → 60 → 120 → ?"),
            numbers = listOf(0, 6, 24, 60, 120),
            correctAnswer = 210,
            hint1 = "Compare each term to nearby cubes: 1³=1, 2³=8, 3³=27, 4³=64, 5³=125.",
            hint2 = "Each term equals n³ - n for n = 1, 2, 3, 4, 5.",
            hint3 = "For n = 6: 6³ - 6 = 216 - 6 = 210.",
            explanation = "The rule is n³ - n. For n = 6: 216 - 6 = 210."
        ),

        // Level 47: Geometric Progression Columns
        LevelData(
            id = 47,
            title = "Geometric Matrix",
            type = PuzzleType.NUMBER_GRID,
            difficulty = Difficulty.EXPERT,
            question = "Each column forms a geometric relationship.",
            gridData = listOf(
                listOf("2", "3", "4"),
                listOf("6", "12", "10"),
                listOf("18", "48", "?")
            ),
            correctAnswer = 25,
            hint1 = "Column 1: 2 × 18 = 36 = 6².",
            hint2 = "In every column: Top × Bottom = (Middle)².",
            hint3 = "4 × ? = 10² = 100, so ? = 100 / 4 = 25.",
            explanation = "Top × Bottom = Middle²: 4 × 25 = 10² = 100. The missing number is 25."
        ),

        // Level 48: Alternating Hexagon Products
        LevelData(
            id = 48,
            title = "Hexagonal Triad",
            type = PuzzleType.GEOMETRIC_HEXAGON,
            difficulty = Difficulty.EXPERT,
            question = "The center equals the difference between alternating triad products.",
            shapeLabels = listOf(
                "Clockwise Vertices: [2, 1, 6, 3, 7, 2]",
                "Formula: (Triad 1) - (Triad 2) = Center",
                "Center: ?"
            ),
            numbers = listOf(2, 1, 6, 3, 7, 2),
            correctAnswer = 78,
            hint1 = "Take alternating vertices for Triad 1: 2, 6, 7.",
            hint2 = "Take the remaining three vertices for Triad 2: 1, 3, 2.",
            hint3 = "(2 × 6 × 7) - (1 × 3 × 2) = 84 - 6 = 78.",
            explanation = "(2 × 6 × 7) - (1 × 3 × 2) = 84 - 6 = 78."
        ),

        // Level 49: Clock Modular Arithmetic
        LevelData(
            id = 49,
            title = "The Clockwork Realm",
            type = PuzzleType.RELATIONSHIP,
            difficulty = Difficulty.EXPERT,
            question = "This puzzle follows circular 12-hour clock addition.",
            equations = listOf(
                "8 + 6 = 2",
                "9 + 7 = 4",
                "11 + 5 = 4",
                "10 + 8 = ?"
            ),
            correctAnswer = 6,
            hint1 = "Think about hours on a traditional 12-hour clock.",
            hint2 = "8 o'clock plus 6 hours is 14:00, which is 2 o'clock.",
            hint3 = "10 + 8 = 18 hours. 18 - 12 = 6 o'clock.",
            explanation = "Modular 12 arithmetic: (10 + 8) mod 12 = 18 mod 12 = 6."
        ),

        // Level 50: Milestone 5! Grand Master Polyhedral Riddle
        LevelData(
            id = 50,
            title = "Grand Master: Polyhedral Crown",
            type = PuzzleType.MIXED_LOGIC,
            difficulty = Difficulty.EXPERT,
            question = "Solve the master system of shapes to evaluate the final expression.",
            equations = listOf(
                "🔴 + 🔺 + 🟦 = 23",
                "🔴 × 🔺 = 48",
                "🔺 × 🟦 = 72",
                "🔶 = 🟦² - (🔴 × 🔺)",
                "(🔶 + 🔴) × (🟦 - 🔺) = ?"
            ),
            correctAnswer = 39,
            hint1 = "From 🔴 × 🔺 = 48 and 🔺 × 🟦 = 72, find the integer values: 🔴 = 6, 🔺 = 8, 🟦 = 9.",
            hint2 = "Verify: 6 + 8 + 9 = 23. Calculate Diamond: 🔶 = 9² - 48 = 81 - 48 = 33.",
            hint3 = "(🔶 + 🔴) × (🟦 - 🔺) = (33 + 6) × (9 - 8) = 39 × 1 = 39.",
            explanation = "🔴 = 6, 🔺 = 8, 🟦 = 9. 🔶 = 81 - 48 = 33. Finally, (33 + 6) × (9 - 8) = 39."
        )
    )

    fun getLevel(id: Int): LevelData = levels.firstOrNull { it.id == id } ?: levels.first()
}
