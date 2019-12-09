package com.example.clockcustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ClockView : View {
    lateinit var outerCirclePaint: Paint
    private var generalSize: Float = 0f
    lateinit var innerCirclePaint: Paint
    lateinit var tickPaint: Paint
    private var innerCircleSize: Float = 0f
    private var ticksRadius = 0f
    constructor(context: Context?) : super(context) {

    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    {
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    fun init(attrs: AttributeSet?) {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ClockView)
        generalSize = ta.getFloat(R.styleable.ClockView_size, 0f)
        outerCirclePaint = Paint().apply {
            color = ta.getColor(R.styleable.ClockView_outerCircleColor, Color.parseColor("#edf7ff"))
        }

        innerCirclePaint = Paint().apply {
            color = ta.getColor(R.styleable.ClockView_innerCircleColor, Color.parseColor("#e6eef6"))
        }

        innerCircleSize = ta.getFloat(R.styleable.ClockView_innerCircleSize, 0f)

        tickPaint = Paint().apply {
            color = Color.WHITE;
            strokeWidth = 1.5f;
            style = Paint.Style.STROKE;
        }
        ticksRadius = (generalSize - innerCircleSize) / 2

        ta.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val middleX = canvas?.width!! / 2f
        val middleY = canvas?.height!! / 2f
        canvas.drawCircle(middleX, middleY, generalSize, outerCirclePaint)
        canvas.drawCircle(middleX, middleY, innerCircleSize, innerCirclePaint)
        canvas.drawCircle(middleX, middleY, innerCircleSize + 25, tickPaint)
    }

}