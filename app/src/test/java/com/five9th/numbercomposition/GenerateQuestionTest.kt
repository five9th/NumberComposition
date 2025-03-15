package com.five9th.numbercomposition

import com.five9th.numbercomposition.data.GameRepositoryImpl
import com.five9th.numbercomposition.domain.entities.Question
import org.junit.Assert.*
import org.junit.Test

class GenerateQuestionTest {

    private fun generateQuestion(maxSum: Int, countOfOptions: Int): Question {
        return GameRepositoryImpl.generateQuestion(maxSum, countOfOptions)
    }

    @Test
    fun `generateQuestion should return correct number of options`() {
        val maxSum = 20
        val countOfOptions = 4
        val question = generateQuestion(maxSum, countOfOptions)

        assertEquals(countOfOptions, question.secondValueOptions.size)
    }

    @Test
    fun `generateQuestion should have unique options`() {
        val maxSum = 20
        val countOfOptions = 4
        repeat(100) { // Run multiple times to ensure randomness doesn't cause false positives
            val question = generateQuestion(maxSum, countOfOptions)

            val options = question.secondValueOptions
            val uniqueOptions = options.toSet()

            assertEquals("All options should be unique", options.size, uniqueOptions.size)
        }
    }

    @Test
    fun `generateQuestion should always include the correct answer`() {
        val maxSum = 20
        val countOfOptions = 4
        repeat(100) { // Run multiple times to ensure correctness
            val question = generateQuestion(maxSum, countOfOptions)
            val correctAnswer = question.sum - question.firstValue

            assertTrue("Options should contain correct answer",
                question.secondValueOptions.contains(correctAnswer))
        }
    }

    @Test
    fun `generateQuestion should not exceed maxSum`() {
        val maxSum = 10
        val countOfOptions = 4
        val question = generateQuestion(maxSum, countOfOptions)

        assertTrue("Sum should be within allowed range", question.sum in 2..maxSum)
    }

    @Test
    fun `generateQuestion should generate valid firstValue`() {
        val maxSum = 20
        val countOfOptions = 4
        val question = generateQuestion(maxSum, countOfOptions)

        assertTrue("First value should be less than sum", question.firstValue < question.sum)
    }
}
