package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.five9th.numbercomposition.databinding.FragmentInGameBinding
import com.five9th.numbercomposition.domain.entities.GameResult
import com.five9th.numbercomposition.domain.entities.Question
import com.five9th.numbercomposition.presentation.viewmodels.InGameViewmodel
import com.five9th.numbercomposition.presentation.viewmodels.InGameViewmodelFactory
import java.util.Locale

class InGameFragment : BaseFragment<FragmentInGameBinding>(
    FragmentInGameBinding::inflate
) {
    private val args by navArgs<InGameFragmentArgs>()

    private val viewModelFactory by lazy {
        InGameViewmodelFactory(requireActivity().application, args.level)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[InGameViewmodel::class.java]
    }

    private lateinit var optionButtons: Array<TextView>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOptionButtonsArr()
        bindData()
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

    private fun bindData() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun subscribeToLDs() {
        viewModel.gameResultLD.observe(viewLifecycleOwner, ::launchGameResultFragment)
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
        findNavController().navigate(
            InGameFragmentDirections.actionInGameFragmentToGameResultFragment(gameResult)
        )
    }
}