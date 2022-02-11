package com.seda.oyun

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_oyun_ekrani.*
import kotlinx.android.synthetic.main.activity_sonuc_ekrani.*

class SonucEkrani : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sonuc_ekrani)

        val skor=intent.getIntExtra("skor",0)
        textView2.text= skor.toString()


        val sp = getSharedPreferences("Sonuc", Context.MODE_PRIVATE)
        val enYuksekSkor= sp.getInt("enYuksekSkor",0)
        if(skor> enYuksekSkor){
            val editor =sp.edit()
            editor.putInt("enYuksekSkor",skor)
            editor.commit()
            textView3.text=skor.toString()
        }else{
            textView3.text=enYuksekSkor.toString()

        }
         start.setOnClickListener {
             val intent = Intent(this,MainActivity::class.java)
             startActivity(intent)
         }
    }
}