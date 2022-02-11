package com.seda.oyun

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        balikivmeOzelligi()
        kirmiziivmeOzelligi()
        orangeivmeOzelligi()
        greenivmeOzelligi()
        balik1ivmeOzelligi()
        balik2ivmeOzelligi()
        oyunabasla()

        oyunabasla.setOnClickListener {
            val intent = Intent(this,OyunEkrani::class.java)
            startActivity(intent)
        }
  nasilOynanir.setOnClickListener {
      bilgilendirme()
  }

    }
fun oyunabasla(){

        val t = ObjectAnimator.ofFloat(oyunabasla,"translationY",1000.0f,0.0f).apply {
            duration = 3000
        }

        t.start()

}

    fun balikivmeOzelligi(){
        val t = ObjectAnimator.ofFloat(balik,"translationY",0.0f,200.0f).apply {
            duration = 3000
            repeatCount= ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE

        }

        t.start()
    }
    fun balik1ivmeOzelligi(){
        val t = ObjectAnimator.ofFloat(balik1,"translationY",0.0f,200.0f).apply {
            duration = 3000
            repeatCount= ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE

        }

        t.start()
    }
    fun balik2ivmeOzelligi(){
        val t = ObjectAnimator.ofFloat(balik2,"translationY",0.0f,200.0f).apply {
            duration = 3000
            repeatCount= ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE

        }

        t.start()
    }
    fun kirmiziivmeOzelligi(){
        val t = ObjectAnimator.ofFloat(kirmizi,"translationY",0.0f,200.0f).apply {
            duration = 3000
            repeatCount= ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE

        }

        t.start()
    }
    fun orangeivmeOzelligi(){
        val t = ObjectAnimator.ofFloat(orange,"translationY",0.0f,200.0f).apply {
            duration = 3000
            repeatCount= ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE

        }

        t.start()
    }
    fun greenivmeOzelligi(){
        val t = ObjectAnimator.ofFloat(green,"translationY",0.0f,200.0f).apply {
            duration = 3000
            repeatCount= ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE

        }

        t.start()
    }
    fun bilgilendirme(){
        val tasarim =layoutInflater.inflate(R.layout.dialog,null)
        val ad =AlertDialog.Builder(this)

   ad.setView(tasarim)
     ad.show()
    }
}