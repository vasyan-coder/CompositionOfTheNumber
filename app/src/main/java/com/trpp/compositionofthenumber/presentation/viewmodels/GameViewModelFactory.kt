package com.trpp.compositionofthenumber.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trpp.compositionofthenumber.domain.entity.Level

class GameViewModelFactory(
    private val level: Level,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameProcessViewModel::class.java)) {
            return GameProcessViewModel(application, level) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}