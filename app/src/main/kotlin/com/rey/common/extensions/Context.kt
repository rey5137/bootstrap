package com.rey.common.extensions

import android.content.Context
import android.content.res.Resources
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.widget.Toast
import com.rey.dependency.HasComponent

/**
 * Created by Rey on 12/1/16.
 */

inline fun <reified T> Context.getComponent(): T {
    if (this !is HasComponent<*>)
        throw IllegalArgumentException("context must be instance of HasComponent.")
    @Suppress("UNCHECKED_CAST")
    return component as T
}

fun Context.showToast(@StringRes resId: Int, duration: Int) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.showToast(message: CharSequence, duration: Int) {
    Toast.makeText(this, message, duration).show()
}

fun Context.getColor(id: Int, theme: Resources.Theme? = null): Int
        = ResourcesCompat.getColor(resources, id, theme)

fun Context.getDimen(id: Int): Int
        = resources.getDimensionPixelSize(id)

fun Context.dpToPx(value: Int): Int
        = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics) + 0.5F).toInt()

fun Fragment.showToast(@StringRes resId: Int, duration: Int) {
    context.showToast(resId, duration)
}

fun Fragment.showToast(message: CharSequence, duration: Int) {
    context.showToast(message, duration)
}

fun Fragment.getColor(id: Int, theme: Resources.Theme? = null): Int
        = ResourcesCompat.getColor(context.resources, id, theme)

fun Fragment.getDimen(id: Int): Int
        = context.resources.getDimensionPixelSize(id)

fun RecyclerView.ViewHolder.getColor(id: Int, theme: Resources.Theme? = null): Int
        = ResourcesCompat.getColor(itemView.context.resources, id, theme)

fun RecyclerView.ViewHolder.getDimen(id: Int): Int
        = itemView.context.resources.getDimensionPixelSize(id)

fun RecyclerView.ViewHolder.getString(id: Int): CharSequence
        = itemView.context.resources.getString(id)

fun RecyclerView.ViewHolder.getString(id: Int, vararg data : Any): CharSequence
        = itemView.context.resources.getString(id, data)