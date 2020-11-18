package promax.dohaumen.game1

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class Game(context: Context?) : View(context) {

    private var fishCuiXuong: Bitmap
    private var fishLaoLen: Bitmap
    private lateinit var fishCurent: Bitmap
    private val fishX = 100f
    private var fishY = 100f

    private var cot: Bitmap
    var paint: Paint = Paint()


    val HIGHT_COT = getScreenHeight()/2 -200

    init {

        fishCuiXuong = BitmapFactory.decodeResource(resources, R.drawable.fish_22)
        fishLaoLen = BitmapFactory.decodeResource(resources, R.drawable.fish_11)
        cot = BitmapFactory.decodeResource(resources, R.drawable.cot)

        fishLaoLen = Bitmap.createScaledBitmap(fishLaoLen, 200, 150, true)
        fishCuiXuong = Bitmap.createScaledBitmap(fishCuiXuong, 200, 150, true)
        cot = Bitmap.createScaledBitmap(cot, 100, HIGHT_COT.toInt(), true)
        paint.color = Color.BLUE
        paint.textSize = 100f
    }



    var cot1X = getScreenWidth() - 100f
    var cot1Y = 0f

    var cot2X = getScreenWidth() - 100f
    var cot2Y = getScreenHeight()- HIGHT_COT

    var diem = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        cot1X = width.toFloat() - 199

        canvas!!.drawBitmap(cot, cot1X, cot1Y, null)
        canvas.drawBitmap(cot, cot2X, cot2Y, null)

        if (ontClick) {
            fishCurent = fishLaoLen
        } else {
            fishCurent = fishCuiXuong
        }
        canvas.drawBitmap(fishCurent, fishX, fishY, null)

//
        if (fishY >= height-200f) {
            fishY = height-200f
        } else {
            fishY += 20
        }

        if (fishY <= 0) {
            fishY = 0f
        }

        if (cot1X < -100) {
            cot1X = getScreenWidth().toFloat()
            cot2X = getScreenWidth().toFloat()
        }
        cot1X -=15
        cot2X -=15
        canvas.drawText("$diem", getScreenWidth()/2, 100f, paint)

        if (gameOver()) {
            diem = 0
            Toast.makeText(context, "Gamme ioverr", Toast.LENGTH_SHORT).show()
            canvas!!.drawBitmap(cot, cot1X, cot1Y, paint)
            canvas.drawBitmap(cot, cot2X, cot2Y, paint)
        } else {
            if (fishX+200 == cot1X) diem++
        }



    }

    var ontClick = false

    fun gameOver(): Boolean {
        if ((fishY <= HIGHT_COT && fishX+200 >= cot1X) || (fishY >= getScreenHeight() - HIGHT_COT && fishX+200 == cot2X)) {
            return true
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            object:CountDownTimer(100, 10) {
                override fun onTick(millisUntilFinished: Long) {
                    fishY -= 50
                    ontClick = true

                }

                override fun onFinish() {
                    ontClick = false
                }
            }.start()
        }

        return true
    }


    fun getScreenWidth(): Float {
        return Resources.getSystem().getDisplayMetrics().widthPixels.toFloat()
    }

    fun getScreenHeight(): Float {
        return Resources.getSystem().getDisplayMetrics().heightPixels.toFloat()
    }












}