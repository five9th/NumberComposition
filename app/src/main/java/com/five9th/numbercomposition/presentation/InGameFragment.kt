package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.five9th.numbercomposition.databinding.FragmentInGameBinding
import com.five9th.numbercomposition.domain.entities.GameResult
import com.five9th.numbercomposition.presentation.viewmodels.InGameViewmodel
import com.five9th.numbercomposition.presentation.viewmodels.InGameViewmodelFactory

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()
        subscribeToLDs()
    }

    private fun bindData() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun subscribeToLDs() {
        viewModel.gameResultLD.observe(viewLifecycleOwner, ::launchGameResultFragment)
    }

    private fun launchGameResultFragment(gameResult: GameResult) {
        findNavController().navigate(
            InGameFragmentDirections.actionInGameFragmentToGameResultFragment(gameResult)
        )
    }
}