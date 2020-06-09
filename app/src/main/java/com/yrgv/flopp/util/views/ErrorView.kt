package com.yrgv.flopp.util.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textview.MaterialTextView
import com.yrgv.flopp.R
import com.yrgv.flopp.util.SimpleCallback
import com.yrgv.flopp.util.setThrottledClickListener

/**
 * A simple view that can be used for error cases, shows a message and a button.
 */
class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_error, this, true)
    }

    private val retryButton = findViewById<Button>(R.id.error_view_retry_button)
    private val messageView = findViewById<MaterialTextView>(R.id.error_view_message)

    fun setRetryButtonClickListener(callback: SimpleCallback) {
        retryButton.setThrottledClickListener {
            callback()
        }
    }

    /**
     * Set the error message to be displayed
     * */
    fun setMessage(@StringRes messageResId: Int) {
        messageView.text = context.getString(messageResId)
    }

}