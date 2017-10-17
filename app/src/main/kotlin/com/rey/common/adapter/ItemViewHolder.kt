package com.rey.common.adapter

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Rey on 7/7/2016.
 */
open class ItemViewHolder<T : Item>(parent: ViewGroup, resId: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(resId, parent, false)) {

    var item: T? = null

    init {
        itemView.tag = this
    }

    open fun onBindItem(item: T){
        this.item = item
    }

    open fun onBindItemWithPayload(item: T, payloads: List<Any>?){
        onBindItem(item)
    }

    @CallSuper
    open fun onViewRecycled() {
        item = null
    }

}
