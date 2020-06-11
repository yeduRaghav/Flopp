package com.yrgv.flopp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yrgv.flopp.data.ListingsRepository
import com.yrgv.flopp.ui.detail.DetailScreenViewModel
import com.yrgv.flopp.ui.main.MainScreenViewModel

/**
 * Factory to provide viewModels
 */
class ViewModelFactory private constructor(private val application: Application) :
    ViewModelProvider.Factory {

    companion object {
        private lateinit var instance : ViewModelFactory

        fun getInstance(application: Application): ViewModelFactory {
            if(!this::instance.isInitialized) {
                instance = ViewModelFactory(application)
            }
            return instance
        }
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainScreenViewModel::class.java) -> {
                MainScreenViewModel(ListingsRepository.getInstance(application)) as T
            }
            modelClass.isAssignableFrom(DetailScreenViewModel::class.java) -> {
                DetailScreenViewModel(ListingsRepository.getInstance(application)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class :${modelClass.canonicalName}")
        }
    }
}