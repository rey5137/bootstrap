package com.rey.common.adapter

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Rey on 9/13/2016.
 */
abstract class ItemAdapter<T: Item> : RecyclerView.Adapter<ItemViewHolder<T>>() {

    internal var recyclerView: RecyclerView? = null
    var items: List<T> = listOf()

    override fun getItemCount(): Int
            = items.size

    fun getItemAt(position: Int): T?
            = if (position < 0 || position > itemCount - 1) null else items[position]

    abstract fun buildViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<out T>

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<T> =
            buildViewHolder(parent, viewType) as ItemViewHolder<T>

    @CallSuper
    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int) {
        getItemAt(position)?.let { item ->
            holder.onBindItem(item)
        }
    }

    @CallSuper
    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int, payloads: MutableList<Any>?) {
        getItemAt(position)?.let { item ->
            holder.onBindItemWithPayload(item, payloads)
        }
    }

    @CallSuper
    override fun onViewRecycled(holder: ItemViewHolder<T>) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }
}
