package com.five9th.numbercomposition.data

import com.five9th.numbercomposition.domain.entities.GameSettings
import com.five9th.numbercomposition.domain.entities.Level
import com.five9th.numbercomposition.domain.entities.Question
import com.five9th.numbercomposition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_OPTION_VALUE = 1

    override fun generateQuestion(maxSum: Int, countOfOptions: Int): Question {
        if (maxSum < countOfOptions) {
            throw IllegalArgumentException(
                "maxSum ($maxSum) can't be less than countOfOptions ($countOfOptions)"
            )
        }

        val sum = Random.nextInt(MIN_SUM_VALUE, maxSum + 1)
        val firstVal = Random.nextInt(MIN_OPTION_VALUE, sum - MIN_OPTION_VALUE + 1)

        val rightAnswer = sum - firstVal

        val minOption = max(rightAnswer - countOfOptions, MIN_OPTION_VALUE)
        val maxOption = min(rightAnswer + countOfOptions, maxSum)

        val possibleOptions = minOption..maxOption

//        println("maxSum = $maxSum; countOfOptions = $countOfOptions\nsum = $sum\nfirst = $firstVal\nrightAnswer = $rightAnswer\nminOption = $minOption\nmaxOption = $maxOption\noptionsCount = ${possibleOptions.count()}")

        if (possibleOptions.count() < countOfOptions) {
            throw Exception("Unavailable to fill options list the size of $countOfOptions" +
                    " with unique values between $minOption and $maxOption")
        }

        val options = (possibleOptions - rightAnswer).shuffled().take(countOfOptions) + rightAnswer

        return Question(
            sum,
            firstVal,
            options
        )
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> GameSettings(
                10,
                10,
                10,
                50
            )
            Level.EASY -> GameSettings(
                60,
                10,
                14,
                80
            )
            Level.NORMAL -> GameSettings(
                40,
                20,
                20,
                90
            )
            Level.HARD -> GameSettings(
                40,
                30,
                20,
                90
            )
        }
    }
}