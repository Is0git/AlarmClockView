package com.example.clockcustomview

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import androidx.core.os.HandlerCompat
import java.util.*
import java.util.concurrent.Executors

class ClockView : View {
    lateinit var outerCirclePaint: Paint
    private var generalSize: Float = 0f
    lateinit var innerCirclePaint: Paint
    lateinit var tickPaint: Paint
    private var innerCircleSize: Float = 0f
    private var ticksRadius = 0f
    lateinit var path: Path
    lateinit var ticksRect: RectF

    var currentTimeMs: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    var totalTimeMs = 0
    var timeHandler = Handler()
    val timeThread : Thread by lazy { Thread(Runnable {

    }) }
    constructor(context: Context?) : super(context) {

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
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
            color = Color.RED;

        }
        ticksRect = RectF()
        ticksRadius = (generalSize - innerCircleSize) / 2
        path = Path()
        ta.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val middleX = canvas?.width!! / 2f
        val middleY = canvas?.height!! / 2f
        path.apply {

        }
        ticksRect.apply {
            left = 100f
            right = canvas?.width.toFloat() - 100
            top = 100f
            bottom = canvas?.height.toFloat() - 100
        }
        canvas.drawCircle(middleX, middleY, generalSize, outerCirclePaint)
        canvas.drawCircle(middleX, middleY, innerCircleSize, innerCirclePaint)
        val percent = (360 * countPercent(totalTimeMs, currentTimeMs)) / 100
        canvas.drawArc(ticksRect, 270f, percent, true, tickPaint)

    }


    fun startClock() {
        if(timeThread.isAlive) {
            timeThread.interrupt()
        }
       timeThread.start()
    }

    fun countPercent(totalTimeMs: Int, currentTimeMs: Int): Float =
        (currentTimeMs.toFloat() / totalTimeMs) * 100f
}