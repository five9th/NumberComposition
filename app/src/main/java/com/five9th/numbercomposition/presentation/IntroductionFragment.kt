package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.five9th.numbercomposition.databinding.FragmentIntroductionBinding

class IntroductionFragment : BaseFragment<FragmentIntroductionBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIntroductionBinding {
        return FragmentIntroductionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            //todo
        }
    }
}