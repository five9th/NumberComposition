package com.five9th.numbercomposition.domain.usecases

import com.five9th.numbercomposition.domain.entities.Question
import com.five9th.numbercomposition.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSum: Int): Question {
        return repository.generateQuestion(maxSum, COUNT_OF_OPTIONS)
    }

    companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}