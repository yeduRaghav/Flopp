package com.yrgv.flopp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.yrgv.flopp.R
import com.yrgv.flopp.ui.ViewModelFactory

class MainScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))
            .get(MainScreenViewModel::class.java)
        //todo:subscribe for data
    }
}
