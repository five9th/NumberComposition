package com.five9th.numbercomposition.presentation.viewmodels

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.data.GameRepositoryImpl
import com.five9th.numbercomposition.domain.entities.GameResult
import com.five9th.numbercomposition.domain.entities.GameSettings
import com.five9th.numbercomposition.domain.entities.Level
import com.five9th.numbercomposition.domain.entities.Question
import com.five9th.numbercomposition.domain.usecases.GenerateQuestionUseCase
import com.five9th.numbercomposition.domain.usecases.GetGameSettingsUseCase
import java.util.Locale

class InGameViewmodel(application: Application) : AndroidViewModel(application) {

    private val context = application

    private val repository = GameRepositoryImpl // Temporary solution

    private var _level: Level? = null
    private val level: Level
        get() = _level ?: throw Exception("Game is not started")

    private var _gameSettings: GameSettings? = null
    private val gameSettings: GameSettings
        get() = _gameSettings ?: throw Exception("Game is not started")

    private var timer: CountDownTimer? = null

    private var answersTotal = 0
    private var answersRight = 0


    // ------ UseCases ------ //
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)


    // ------ LiveData ------ //
    private val _questionLD = MutableLiveData<Question>()
    val questionLD: LiveData<Question>
        get() = _questionLD

    private val _rightAnswersStrLD = MutableLiveData<String>()
    val rightAnswersStrLD: LiveData<String>
        get() = _rightAnswersStrLD

    private val _rightAnswersPercentLD = MutableLiveData<Int>()
    val rightAnswersPercentLD: LiveData<Int>
        get() = _rightAnswersPercentLD

    private val _requiredPercentLD = MutableLiveData<Int>()
    val requiredPercentLD: LiveData<Int>
        get() = _requiredPercentLD

    private val _isCountEnoughLD = MutableLiveData<Boolean>()
    val isCountEnoughLD: LiveData<Boolean>
        get() = _isCountEnoughLD

    private val _isPercentEnoughLD = MutableLiveData<Boolean>()
    val isPercentEnoughLD: LiveData<Boolean>
        get() = _isPercentEnoughLD


    private val _gameTimerLD = MutableLiveData<String>()
    val gameTimerLD: LiveData<String>
        get() = _gameTimerLD

    /** This object getting a value means that the game is over */
    private val _gameResultLD = MutableLiveData<GameResult>()
    val gameResultLD: LiveData<GameResult>
        get() = _gameResultLD


    fun startGame(level: Level) {
        getGameSettings(level)

        updateAndSetNewQuestion()

        startTimer()
    }

    private fun getGameSettings(level: Level) {
        _level = level
        _gameSettings = getGameSettingsUseCase(level)
    }

    private fun updateAndSetNewQuestion() {
        updateValues()
        setNewQuestion()
    }

    private fun updateValues() {
        val rightAnswersPercent = (answersRight * 100.0 / answersTotal).toInt()
        val requiredPercent = gameSettings.minRightPercent
        val minRightAnswersCount = gameSettings.minRightAnswersCount

        _rightAnswersStrLD.value = String.format(
            context.resources.getString(R.string.progress_answers),
            answersRight.toString(),
            minRightAnswersCount.toString()
        )

        _rightAnswersPercentLD.value = rightAnswersPercent
        _requiredPercentLD.value = requiredPercent

        _isCountEnoughLD.value = answersRight >= minRightAnswersCount
        _isPercentEnoughLD.value = rightAnswersPercent >= requiredPercent
    }

    private fun setNewQuestion() {
        val question = generateQuestionUseCase(gameSettings.maxSumValue)

        // Sort the options list so the options will be displayed in ascending order
        _questionLD.value = question.copy(
            sum = question.sum,
            firstValue = question.firstValue,
            secondValueOptions = question.secondValueOptions.sorted()
        )
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECOND,
            MILLIS_IN_SECOND
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _gameTimerLD.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }

        }

        timer?.start()
    }

    private fun formatTime(millis: Long): String {
        val secondsTotal = millis / MILLIS_IN_SECOND
        val minutesLeft = secondsTotal / SECONDS_IN_MINUTE
        val secondsLeft = secondsTotal - (minutesLeft * SECONDS_IN_MINUTE)

        return String.format(Locale.getDefault(), "%02d:%02d", minutesLeft, secondsLeft)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    fun giveAnswer(question: Question, answerIndex: Int) {
        if (isAnswerCorrect(question, answerIndex)) {
            answersRight++
        }
        answersTotal++

        updateAndSetNewQuestion()
    }

    private fun isAnswerCorrect(question: Question, answerIndex: Int): Boolean {
        val first = question.firstValue
        val second = question.secondValueOptions[answerIndex]
        return first + second == question.sum
    }

    private fun finishGame() {
        _gameResultLD.value = GameResult(
            isCountEnoughLD.value == true && isPercentEnoughLD.value == true,
            answersTotal,
            answersRight,
            gameSettings
        )
    }

    companion object {
        private const val MILLIS_IN_SECOND = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }
}