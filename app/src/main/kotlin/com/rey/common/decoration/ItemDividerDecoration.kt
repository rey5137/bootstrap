package com.rey.common.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by Rey on 9/13/2016.
 */
class ItemDividerDecoration(private val divider: Divider,
                            reverse: Boolean,
                            drawOver: Boolean,
                            callback: ItemDecorationCallback) : BaseItemDecoration(reverse, drawOver, callback) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.strokeWidth = divider.height.toFloat()
        paint.style = Paint.Style.STROKE
    }

    override fun onDrawDecor(c: Canvas, parent: RecyclerView, state: RecyclerView.State?, child: View) {
        if (reverse) {
            val params = child.layoutParams as RecyclerView.LayoutParams
            val y = (child.top - params.topMargin) + child.translationY + divider.height / 2f
            drawDivider(c, divider.paddingLeft.toFloat(), (parent.width - divider.paddingRight).toFloat(), y, child.alpha)
        } else {
            val params = child.layoutParams as RecyclerView.LayoutParams
            val y = child.bottom + params.bottomMargin + child.translationY - divider.height / 2f
            drawDivider(c, divider.paddingLeft.toFloat(), (parent.width - divider.paddingRight).toFloat(), y, child.alpha)
        }
    }

    private fun drawDivider(c: Canvas, left: Float, right: Float, y: Float, alpha: Float) {
        paint.color = getColor(divider.color, alpha)
        c.drawLine(left, y, right, y, paint)
    }

    private fun getColor(baseColor: Int, alphaPercent: Float): Int {
        val alpha = Math.round(Color.alpha(baseColor) * alphaPercent)
        return baseColor and 0x00FFFFFF or (alpha shl 24)
    }

}
