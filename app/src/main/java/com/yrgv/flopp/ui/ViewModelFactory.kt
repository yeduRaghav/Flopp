package com.yrgv.flopp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yrgv.flopp.ui.main.MainScreenViewModel

/**
 * Factory to provide viewModels
 */
class ViewModelFactory private constructor(): ViewModelProvider.Factory {

    companion object {
        private lateinit var instance : ViewModelFactory

        //todo: can be by lazy ?
        fun getInstance() : ViewModelFactory {
            if(!this::instance.isInitialized) {
                instance =
                    ViewModelFactory()
            }
            return instance
        }

    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainScreenViewModel::class.java) -> MainScreenViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class :${modelClass.canonicalName}")
        }
    }
}