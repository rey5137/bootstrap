package com.rey.common.decoration

import com.rey.common.adapter.Item

/**
 * Created by Rey on 11/3/2016.
 */
open class SimpleItemDecorationCallback(private val classes: Array<Class<out Item>>) : ItemDecorationCallback {

    protected open fun valid(item: Item?): Boolean {
        if (item != null)
            classes
                    .filter { it.isAssignableFrom(item.javaClass) }
                    .forEach { return true }
        return false
    }

    override fun shouldDecor(item: Item?, nextItem: Item?): Boolean {
        return nextItem != null && valid(item)
    }
}
