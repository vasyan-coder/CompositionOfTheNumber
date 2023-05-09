package com.trpp.compositionofthenumber.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.domain.entity.Type

class GameViewModelFactory(
    private val level: Level,
    private val application: Application,
    private val type: Type
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameProcessViewModel::class.java)) {
            return GameProcessViewModel(application, level, type) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}