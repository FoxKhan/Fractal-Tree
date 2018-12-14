package com.develop.foxkhan.recursivetree.tree

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.develop.foxkhan.recursivetree.R


class TreeView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val branchPaint = Paint()
    private val backgroundPaint = Paint()

    init {
        branchPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)
        branchPaint.style = Paint.Style.STROKE
        branchPaint.isAntiAlias = true
        branchPaint.strokeWidth = 2F

        backgroundPaint.color = -0x71020
    }


    fun startAnimation() {

        val valueAnimator = ValueAnimator.ofFloat(0F, 1F)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue


        }
        valueAnimator.start()
//        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas != null) {
            drawTree(canvas)
        }
    }

    private var cX: Float = -1f
    private var cY: Float = -1f

    var angle = 45f
        set(value) {
            field = value
            invalidate()
        }


    private fun drawTree(canvas: Canvas) {

        cX = width / 2f
        cY = height.toFloat()

        val length = width / 3f

        branch(canvas, length)
    }


    private fun branch(canvas: Canvas, len: Float) {

        if (len < 5) return

        canvas.drawLine(cX, cY, cX, cY - len, branchPaint)
        canvas.translate(0f, -len)

        canvas.save()
        canvas.rotate(angle, cX, cY)
        branch(canvas, len * 0.67f)
        canvas.restore()

        canvas.save()
        canvas.rotate(-angle, cX, cY)
        branch(canvas, len * 0.67f)
        canvas.restore()
    }


/*
    private fun drawTree(canvas: Canvas) {

        cX = width / 2f
        cY = height.toFloat()

        var length = width / 2f

        canvas.drawLine(cX, cY, cX, cY - length, branchPaint)
        cY -= length

        length *= 0.66f

        branch(canvas, length, PI / 4)
        branch(canvas, length, -PI / 4)
    }


    private fun branch(canvas: Canvas, length: Float, angle: Double) {

        val displacedAngle = (angle - PI / 2)

        val cos = cos(displacedAngle).toFloat()
        val sin = sin(displacedAngle).toFloat()

        val x = cX + (cos * length)
        val y = cY + (sin * length)

        canvas.drawLine(cX, cY, x, y, branchPaint)


//        canvas.save()
//        canvas.rotate(angle, cx, cy)
//        canvas.drawLine(cx, cy, cx + dx, cy, paint)
//        canvas.restore()
    }
    */
}