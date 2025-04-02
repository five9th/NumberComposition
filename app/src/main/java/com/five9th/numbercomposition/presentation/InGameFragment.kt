package com.five9th.numbercomposition.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.databinding.FragmentInGameBinding
import com.five9th.numbercomposition.domain.entities.GameResult
import com.five9th.numbercomposition.domain.entities.Level
import com.five9th.numbercomposition.domain.entities.Question
import com.five9th.numbercomposition.presentation.viewmodels.InGameViewmodel
import com.five9th.numbercomposition.presentation.viewmodels.InGameViewmodelFactory
import java.util.Locale

class InGameFragment : BaseFragment<FragmentInGameBinding>(
    FragmentInGameBinding::inflate
) {
    private val tag = "InGameFragment"

    private lateinit var level: Level

    private val viewModelFactory by lazy {
        InGameViewmodelFactory(requireActivity().application, level)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[InGameViewmodel::class.java]
    }

    private lateinit var optionButtons: Array<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        level = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
        } else {
            requireArguments().getParcelable(KEY_LEVEL) as Level?
        } ?: throw RuntimeException("Level is null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOptionButtonsArr()
        subscribeToLDs()
        setListeners()
    }

    private fun initOptionButtonsArr() {
        optionButtons = arrayOf(
            binding.tvOption1,
            binding.tvOption2,
            binding.tvOption3,
            binding.tvOption4,
            binding.tvOption5,
            binding.tvOption6
            )
    }

    private fun subscribeToLDs() {
        viewModel.gameTimerLD.observe(viewLifecycleOwner) { timeStr ->
            binding.tvTimer.text = timeStr
        }

        viewModel.rightAnswersStrLD.observe(viewLifecycleOwner) { value ->
            binding.tvAnswersProgress.text = value
        }

        viewModel.requiredPercentLD.observe(viewLifecycleOwner) { percent ->
            binding.pbRightPercent.secondaryProgress = percent
        }

        viewModel.rightAnswersPercentLD.observe(viewLifecycleOwner) { percent ->
            binding.pbRightPercent.setProgress(percent, true)
        }

        viewModel.isPercentEnoughLD.observe(viewLifecycleOwner) { value ->
            val color = getColor(value)
            binding.pbRightPercent.progressDrawable.setTint(color)
        }

        viewModel.isCountEnoughLD.observe(viewLifecycleOwner) { value ->
            val color = getColor(value)
            binding.tvAnswersProgress.setTextColor(color)
        }

        viewModel.questionLD.observe(viewLifecycleOwner, ::parseQuestion)

        viewModel.gameResultLD.observe(viewLifecycleOwner, ::launchGameResultFragment)
    }

    private fun getColor(isSuccess: Boolean): Int {
        return if (isSuccess) {
            ContextCompat.getColor(requireContext(), R.color.green)
        } else {
            ContextCompat.getColor(requireContext(), R.color.red)
        }
    }

    private fun parseQuestion(question: Question) {
        binding.tvSum.text = intToText(question.sum)

        binding.tvLeftNumber.text = intToText(question.firstValue)

        setOptionButtons(question.secondValueOptions)
    }

    private fun intToText(value: Int): String {
        return String.format(Locale.getDefault(), "%d", value)
    }

    private fun setOptionButtons(options: List<Int>) {
        for (i in optionButtons.indices) {
            optionButtons[i].text = intToText(options[i])
        }
    }

    private fun setListeners() {
        with(binding) {
            tvOption1.setOnClickListener { optionClicked(0) }
            tvOption2.setOnClickListener { optionClicked(1) }
            tvOption3.setOnClickListener { optionClicked(2) }
            tvOption4.setOnClickListener { optionClicked(3) }
            tvOption5.setOnClickListener { optionClicked(4) }
            tvOption6.setOnClickListener { optionClicked(5) }
        }
    }

    private fun optionClicked(btnIndex: Int) {
        val question = viewModel.questionLD.value ?: return

        viewModel.giveAnswer(question, btnIndex)
    }

    private fun launchGameResultFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, GameResultFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val NAME = "InGameFragment"

        private const val OPTIONS_COUNT = 6

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): InGameFragment {
            return InGameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}