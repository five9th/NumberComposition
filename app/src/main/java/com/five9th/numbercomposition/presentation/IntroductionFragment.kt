package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import com.five9th.numbercomposition.databinding.FragmentIntroductionBinding

class IntroductionFragment : BaseFragment<FragmentIntroductionBinding>(
    FragmentIntroductionBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            //todo
        }

        binding.startBtn.text = "hello from binding"
    }
}