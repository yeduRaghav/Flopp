package com.yrgv.flopp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.yrgv.flopp.R
import com.yrgv.flopp.ui.ViewModelFactory
import com.yrgv.flopp.util.hide
import com.yrgv.flopp.util.show
import kotlinx.android.synthetic.main.activity_detail_screen.*
import kotlinx.android.synthetic.main.layout_detail_screen.*

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
        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        detail_screen_error_view.setRetryButtonClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))
            .get(DetailScreenViewModel::class.java)

        getListingId(intent)?.let {
            viewModel.setData(it)
        } ?: finish()

        viewModel.getViewState().observe(this, Observer { state ->
            handleViewState(state)
        })

        viewModel.getListing().observe(this, Observer { listing ->
            handleListing(listing)
        })
    }

    private fun handleListing(listing: DetailedListing) {
        listing_item_title.text = listing.title
        detail_screen_price.text = listing.price
        detail_screen_model.text = listing.model
        detail_screen_lender_name.text = listing.lenderName
        detail_screen_location.text = listing.location
        detail_screen_description.text = listing.description
        detail_screen_renter_rating.rating = listing.rating

        Picasso.get()
            .load(listing.image)
            .placeholder(R.drawable.image_placeholder)
            .into(detail_screen_image)
    }

    private fun handleViewState(state: DetailScreenViewModel.ViewState) {
        when (state) {
            DetailScreenViewModel.ViewState.Loading -> showLoadingView()
            DetailScreenViewModel.ViewState.LoadingSuccess -> showValidView()
            DetailScreenViewModel.ViewState.LoadingFailed -> showErrorView()
        }
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