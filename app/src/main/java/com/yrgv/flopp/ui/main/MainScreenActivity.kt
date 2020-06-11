package com.yrgv.flopp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yrgv.flopp.R
import com.yrgv.flopp.ui.ViewModelFactory
import com.yrgv.flopp.ui.detail.DetailScreenActivity
import com.yrgv.flopp.util.hide
import com.yrgv.flopp.util.show
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenActivity : AppCompatActivity() {
    private lateinit var viewModel: MainScreenViewModel
    private val listingsAdapter = ListingsAdapter { item ->
        startActivity(DetailScreenActivity.getIntent(item.id, this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        main_screen_recycler_view.adapter = listingsAdapter
        main_screen_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onListBottomReached()
                }
            }
        })
        main_screen_error_view.setRetryButtonClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))
            .get(MainScreenViewModel::class.java)
        viewModel.getListDataState().observe(this, Observer { state ->
            handleDataState(state)
        })
        viewModel.getListings().observe(this, Observer { listings ->
            listingsAdapter.submitList(listings)
        })
    }

    private fun handleDataState(state: MainScreenViewModel.ListDataState) {
        when (state) {
            MainScreenViewModel.ListDataState.FirstPageLoaded -> showListView()
            MainScreenViewModel.ListDataState.FirstPageLoading -> showLoadingView()
            MainScreenViewModel.ListDataState.FirstPageLoadFailed -> showErrorView()
            MainScreenViewModel.ListDataState.NextPageLoadFailed -> {
                Snackbar.make(
                    main_screen_root,
                    getString(R.string.error_view_default_message),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showListView() {
        main_screen_recycler_view.show()
        main_screen_loading_view.hide()
        main_screen_error_view.hide()
    }

    private fun showLoadingView() {
        main_screen_loading_view.show()
        main_screen_recycler_view.hide()
        main_screen_error_view.hide()
    }

    private fun showErrorView() {
        main_screen_error_view.show()
        main_screen_recycler_view.hide()
        main_screen_loading_view.hide()
    }

}