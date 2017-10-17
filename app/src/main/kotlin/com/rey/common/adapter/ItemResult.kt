package com.rey.common.adapter

import android.support.v7.util.DiffUtil

/**
 * Created by Rey on 5/13/17.
 */
data class ItemResult<T: Item>(val items: List<T> = listOf(),
                               val diffResult: DiffUtil.DiffResult? = null) {

    fun dispatchResult(adapter: ItemAdapter<T>){
        adapter.items = items
        diffResult?.dispatchUpdatesTo(adapter)
    }

}