package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.AllLevelsData
import com.example.data.model.DailyChallengeData
import com.example.data.model.MathCrossData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Math Brain", appName)
    }

    @Test
    fun `verify exactly 50 progressive levels exist with valid answers`() {
        val levels = AllLevelsData.levels
        assertEquals(50, levels.size)

        levels.forEachIndexed { index, level ->
            assertEquals(index + 1, level.id)
            assertTrue("Level ${level.id} title cannot be empty", level.title.isNotEmpty())
            assertTrue("Level ${level.id} question cannot be empty", level.question.isNotEmpty())
            assertTrue("Level ${level.id} hint1 cannot be empty", level.hint1.isNotEmpty())
            assertTrue("Level ${level.id} hint2 cannot be empty", level.hint2.isNotEmpty())
            assertTrue("Level ${level.id} hint3 cannot be empty", level.hint3.isNotEmpty())
            assertTrue("Level ${level.id} explanation cannot be empty", level.explanation.isNotEmpty())
        }
    }

    @Test
    fun `verify math cross levels are valid`() {
        val crosses = MathCrossData.levels
        assertTrue(crosses.isNotEmpty())
        crosses.forEach { cross ->
            assertTrue(cross.title.isNotEmpty())
            assertTrue(cross.gridRows.isNotEmpty())
            assertTrue(cross.correctAnswer > 0)
        }
    }

    @Test
    fun `verify daily challenge produces 10 progressive puzzles`() {
        val today = DailyChallengeData.getTodayKey()
        val puzzles = DailyChallengeData.getDailyPuzzlesForDate(today)
        assertEquals(10, puzzles.size)
        puzzles.forEachIndexed { idx, puzzle ->
            assertEquals(idx + 1, puzzle.dayIndex)
            assertNotNull(puzzle.levelData)
        }
    }
}
