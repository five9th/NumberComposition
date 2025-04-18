package com.five9th.numbercomposition.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.five9th.numbercomposition.domain.entities.Level

class InGameViewmodelFactory(
    private val application: Application,
    private val level: Level
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InGameViewmodel::class.java)) {
            return InGameViewmodel(application, level) as T
        }
        else throw RuntimeException("Unknown viewmodel class: $modelClass")
    }
}