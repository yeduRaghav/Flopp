package com.yrgv.flopp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.yrgv.flopp.R
import com.yrgv.flopp.ui.ViewModelFactory
import com.yrgv.flopp.ui.main.MainScreenViewModel
import com.yrgv.flopp.util.hide
import com.yrgv.flopp.util.show
import kotlinx.android.synthetic.main.activity_detail_screen.*

/**
 * Activity for the detail screen
 */
class DetailScreenActivity : AppCompatActivity() {

    companion object {
        private const val INTENT_KEY_LISTING_ID = "INTENT_KEY_LISTING_ID"

        fun getIntent(listingId: Long, context: Context): Intent {
            return Intent(context, DetailScreenActivity::class.java).apply {
                putExtra(INTENT_KEY_LISTING_ID, listingId)
            }
        }

        private fun getListingId(intent: Intent) :Long? {
            return intent.getLongExtra(INTENT_KEY_LISTING_ID, -1L).takeIf { it > 0L }
        }
    }

    private lateinit var viewModel: DetailScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))
            .get(DetailScreenViewModel::class.java)
        //todo: subscribe to data
    }

    private fun showLoadingView() {
        detail_screen_valid_view.hide()
        detail_screen_error_view.hide()
        detail_screen_loading_view.show()
    }

    private fun showErrorView() {
        detail_screen_valid_view.hide()
        detail_screen_error_view.show()
        detail_screen_loading_view.hide()
    }

    private fun showValidView() {
        detail_screen_valid_view.show()
        detail_screen_error_view.hide()
        detail_screen_loading_view.hide()
    }




}