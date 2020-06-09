package com.yrgv.flopp.util

import android.view.View

/**
 * Extension functions for the View classes for convenience
 */

/**
 * Extension functions for the View classes for convenience
 */
fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Simple debounce implementation
 * */
fun View.setThrottledClickListener(delayInMillis: Long = 500L, runWhenClicked: SimpleCallback) {
    setOnClickListener {
        this.isClickable = false
        this.postDelayed(
            {
                this.isClickable = true
            },
            delayInMillis
        )
        runWhenClicked()
    }
}