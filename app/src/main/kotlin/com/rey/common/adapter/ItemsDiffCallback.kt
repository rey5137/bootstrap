package com.rey.common.adapter

import android.support.v7.util.DiffUtil


/**
 * Created by Rey on 9/5/2016.
 */
open class ItemsDiffCallback<in T: Item>(private val oldItems: List<T>,
                                         private val newItems: List<T>)
    : DiffUtil.Callback() {

    override fun getOldListSize(): Int
            = oldItems.size

    override fun getNewListSize(): Int
            = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = compareItems(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = compareItemContents(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int)
            = getItemChange(oldItems[oldItemPosition], newItems[newItemPosition])

    open fun compareItems(oldItem: T, newItem: T): Boolean
            = oldItem == newItem

    open fun compareItemContents(oldItem: T, newItem: T): Boolean
            = oldItem == newItem

    open fun getItemChange(oldItem: T, newItem: T): Any?
            = null

    fun getDiffResult(detectMove: Boolean = true): DiffUtil.DiffResult
            = DiffUtil.calculateDiff(this, detectMove)

}
