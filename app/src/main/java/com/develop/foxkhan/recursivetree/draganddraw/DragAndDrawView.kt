package com.develop.foxkhan.recursivetree.draganddraw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

import java.util.ArrayList

class DragAndDrawView

@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var mCurrentBox: Box? = null

    private val mBoxPaint: Paint = Paint()
    private val mBackgroundPaint: Paint = Paint()

    private val mBoxen = ArrayList<Box>()

    init {
        mBoxPaint.color = 0x22ff0000
        mBackgroundPaint.color = -0x71020
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val current = PointF(event.x, event.y)

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                mCurrentBox = Box(current)
                mBoxen.add(mCurrentBox!!)
            }

            MotionEvent.ACTION_MOVE -> {
                if (mCurrentBox != null) {
                    mCurrentBox!!.current = current
                    invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                mCurrentBox = null
            }

            MotionEvent.ACTION_CANCEL -> {
                mCurrentBox = null
            }
        }

        return true
    }


    override fun onDraw(canvas: Canvas) {

        canvas.drawPaint(mBackgroundPaint)

        for (box in mBoxen) {
            val left = Math.min(box.origin.x, box.current.x)
            val right = Math.max(box.origin.x, box.current.x)
            val top = Math.min(box.origin.y, box.current.y)
            val bottom = Math.max(box.origin.y, box.current.y)

            canvas.drawRect(left, top, right, bottom, mBoxPaint)
        }
    }
}


