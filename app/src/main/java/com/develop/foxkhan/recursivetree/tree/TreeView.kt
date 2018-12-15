package com.develop.foxkhan.recursivetree.tree

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.develop.foxkhan.recursivetree.R
import kotlin.random.Random


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

    private var strokeWidth = 6

    var angle = 45f
        set(value) {
            field = value
            invalidate()
        }


    private fun drawTree(canvas: Canvas) {

        cX = width / 2f
        cY = height.toFloat()

        val length = width / 5f

        branch(canvas, length)
    }


    private fun branch(canvas: Canvas, len: Float, strokeWidth: Float = 15f) {

        if (len < width / 50f) return
        branchPaint.strokeWidth = strokeWidth

        val random = Random.nextInt(0, 100)
        val lengthDividerL = 0.6f + (0.2f * (random / 100f))
        val strokeWidthDividerL = 0.7f + (0.2f * (random / 100f))



        val randomR = Random.nextInt(0, 100)
        val lengthDividerR = 0.6f + (0.2f * (randomR / 100f))
        val strokeWidthDividerR = 0.7f + (0.2f * (random / 100f))



        canvas.drawLine(cX, cY, cX, cY - len, branchPaint)
        canvas.translate(0f, -len)

        when (Random.nextInt(1, 6)) {

            1 -> {
                canvas.save()
                canvas.rotate(angle + Random.nextInt(-10, 10), cX, cY)
                branch(canvas, len * lengthDividerR, strokeWidth * strokeWidthDividerR)
                canvas.restore()
            }

            2 -> {
                canvas.save()
                canvas.rotate(-angle + Random.nextInt(-10, 10), cX, cY)
                branch(canvas, len * lengthDividerL, strokeWidth * strokeWidthDividerL)
                canvas.restore()
            }
            else -> {
                canvas.save()
                canvas.rotate(angle + Random.nextInt(-10, 10), cX, cY)
                branch(canvas, len * lengthDividerR,strokeWidth * strokeWidthDividerR)
                canvas.restore()

                canvas.save()
                canvas.rotate(-angle + Random.nextInt(-10, 10), cX, cY)
                branch(canvas, len * lengthDividerL, strokeWidth * strokeWidthDividerL)
                canvas.restore()
            }
        }
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