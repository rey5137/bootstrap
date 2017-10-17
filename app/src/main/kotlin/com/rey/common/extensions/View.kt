package com.rey.common.extensions

import android.os.Build
import android.view.View
import android.view.ViewTreeObserver


/**
 * Created by Rey on 12/3/16.
 */
@Suppress("DEPRECATION")
inline fun View.doOnGlobalLayout(crossinline action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            else
                viewTreeObserver.removeGlobalOnLayoutListener(this)
            action.invoke()
        }
    })
}

inline fun View.doOnPreDraw(crossinline action: () -> Boolean) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            return action.invoke()
        }
    })
}