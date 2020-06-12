package com.yrgv.flopp.ui.main

/**
 * Basic behaviour and
 */
class MainScreenActivityTest {


    fun showListViewShowsOnlyListView() {
        //todo: Assert when showListView() is called list view becomes visible, loading view and error view becomes gone
    }

    fun showLoadingViewShowsOnlyLoadingView() {
        //todo: Assert when showLoadingView() is called loading view becomes visible, list view and error view becomes gone
    }

    fun showErrorViewShowsOnlyErrorView() {
        //todo: Assert when showErrorView() is called error view becomes visible, list view and loading view becomes gone
    }

    fun handleDataStateFirstPageLoadedStateInvokesShowsListView() {
        //todo: assert handleDataState() invokes showListView() when FirstPageLoaded is passed in
    }

    fun handleDataStateFirstPageLoadingStateInvokesShowsLoadingView() {
        //todo: assert handleDataState() invokes showLoadingView() when FirstPageLoading is passed in
    }

    fun handleDataStateNextPageLoadFailedStateInvokesSnackbar() {
        //todo: assert handleDataState() invokes showGenericErrorSnackbar() when NextPageLoadFailed is passed in
    }

}