package com.five9th.numbercomposition

import com.five9th.numbercomposition.data.GameRepositoryImpl
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class QuestionGenerationTest {
    @Test
    fun generate1() {
        println("\n=============================")
        for (i in 1..100) {
            val quest = GameRepositoryImpl.generateQuestion(6, 6)
            println(quest)
            println()
        }
        println("=============================")
        assert(true)
    }

    @Test
    fun generate2() {
        println("\n=============================")
        for (i in 1..100) {
            val quest = GameRepositoryImpl.generateQuestion(20, 3)
            println(quest)
            println()
        }
        println("=============================")
        assert(true)
    }
}