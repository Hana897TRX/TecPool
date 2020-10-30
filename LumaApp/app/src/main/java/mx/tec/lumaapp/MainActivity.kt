package mx.tec.lumaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
//    lateinit var gestureDetector: GestureDetectorCompat;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        gestureDetector = GestureDetectorCompat(this, this);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


//    override fun onDown(e: MotionEvent?): Boolean {
//        return false
//    }
//
//    override fun onShowPress(e: MotionEvent?) {
//        Log.d("R","r")
//    }
//
//    override fun onSingleTapUp(e: MotionEvent?): Boolean {
//        return false
//    }
//
//    var boo : Boolean = false;
//
//    override fun onScroll(
//        e1: MotionEvent?,
//        e2: MotionEvent?,
//        distanceX: Float,
//        distanceY: Float
//    ): Boolean {
//        if (distanceX > 0)
//        // Scrolled upward
//            boo = true;
////            }
//        if (e2!!.action == MotionEvent.ACTION_UP && boo) {
//            Toast.makeText(this, "Perro", Toast.LENGTH_SHORT).show()
//            return true
//        } else
//            return false
//    }
//
//    override fun onLongPress(e: MotionEvent?) {
//
//
//    }
//
//    override fun onFling(
//        e1: MotionEvent?,
//        e2: MotionEvent?,
//        velocityX: Float,
//        velocityY: Float
//    ): Boolean {
//        return false
//    }
//
//    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
//        return false
//    }
//
//    override fun onDoubleTap(e: MotionEvent?): Boolean {
//        return false
//    }
//
//    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
//        return false
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        gestureDetector.onTouchEvent(event)
//        return super.onTouchEvent(event)
//    }
}