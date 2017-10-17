package com.rey.common.decoration

import com.rey.common.adapter.Item


/**
 * Created by Rey on 11/3/2016.
 */
interface ItemDecorationCallback {

    fun shouldDecor(item: Item?, nextItem: Item?): Boolean
}
