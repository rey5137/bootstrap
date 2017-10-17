package com.rey.common.decoration

import android.graphics.Canvas
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rey.common.adapter.Item
import com.rey.common.adapter.ItemAdapter

/**
 * Created by Rey on 9/13/2016.
 */
open class BaseItemDecoration(protected var reverse: Boolean = false,
                              protected var drawOver: Boolean = false,
                              protected var callback: ItemDecorationCallback) : RecyclerView.ItemDecoration() {

    protected fun getItemAt(adapter: RecyclerView.Adapter<*>, position: Int): Item? {
        if (adapter !is ItemAdapter<*>)
            throw IllegalArgumentException("Adapter should be extended from ItemAdapter.")
        return adapter.getItemAt(position)
    }

    protected fun shouldDecor(parent: RecyclerView, position: Int): Boolean {
        val nextPosition = if (reverse) position - 1 else position + 1
        return callback.shouldDecor(getItemAt(parent.adapter, position),
                getItemAt(parent.adapter, nextPosition))
    }

    protected open fun onDrawDecor(c: Canvas, parent: RecyclerView, state: RecyclerView.State?, child: View) {
    }

    private fun draw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?){
        val layoutManager = parent.layoutManager as LinearLayoutManager

        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()

        if (firstVisiblePosition < 0)
            return

        if (reverse) {
            for (i in lastVisiblePosition downTo firstVisiblePosition) {
                val child = layoutManager.findViewByPosition(i)
                if (child.visibility == View.VISIBLE && shouldDecor(parent, i))
                    onDrawDecor(c, parent, state, child)
            }
        } else {
            for (i in firstVisiblePosition..lastVisiblePosition) {
                val child = layoutManager.findViewByPosition(i)
                if (child.visibility == View.VISIBLE && shouldDecor(parent, i))
                    onDrawDecor(c, parent, state, child)
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        if(drawOver)
            draw(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        if(!drawOver)
            draw(c, parent, state)
    }

}
