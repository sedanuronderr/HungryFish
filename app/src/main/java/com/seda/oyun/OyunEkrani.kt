package com.seda.oyun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_oyun_ekrani.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.floor

class OyunEkrani : AppCompatActivity() {
    private val timer = Timer()
    private var skor =0
    var cansayi = 3
    lateinit var mAdView : AdView
    //pozisyonlar
    private  var anakarakterX = 0.0f
    private  var anakarakterY = 0.0f
    private var yesiltopX = 0.0f
    private var yesilTopY=0.0f
    private var kirmiziTopX=0.0f
    private var kirmiziTopY=0.0f
    private var turuncuTopX=0.0f
    private var turuncuTopY=0.0f
    //Kontroller
    private var dokunmaKontrol =true
    private var baslangicKontrol =false
//Boyutlar
    private  var ekrangenisligi =0
    private  var ekranyuksekligi =0
    private  var anakarakterGenisligi =0
    private  var anakarakterYuksekligi =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun_ekrani)
        MobileAds.initialize(this) {}



        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        greenball.x=-800.0f
        greenball.y=-800.0f

        redball.x=-1000.0f
        redball.y=-800.0f

        orangeball.x=-800.0f
        orangeball.y=-800.0f

        clickk.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(baslangicKontrol){

                    if(event?.action == MotionEvent.ACTION_DOWN){
                        Log.e("MotionEvent","ekrana dokunuldu")
                        dokunmaKontrol= true
                    }

                    if(event?.action == MotionEvent.ACTION_UP){
                        Log.e("MotionEvent","ekranı bıraktı")
                        dokunmaKontrol =false
                    }

                }

                else{
                    baslangicKontrol =true
                    anakarakterX= karakter.x
                    anakarakterY =karakter.y

                    anakarakterGenisligi= karakter.width
                    anakarakterYuksekligi =karakter.height

                    ekrangenisligi =clickk.width
                    ekranyuksekligi= clickk.height

                    timer.schedule(0,20){
                        Handler(Looper.getMainLooper()).post{
                         anakarakterHareketme()
                            cisimleriHareketEttirme()
                            carpismaKonrol()
                        }
                    }
                }





               return true
            }

        })




    }

    fun anakarakterHareketme(){
        val anakarakterhız= ekranyuksekligi/60.0f
        if(dokunmaKontrol){
            anakarakterY-=anakarakterhız // yukarı doğru azalır
        }else{
            anakarakterY+=anakarakterhız// aşağı doğru artar

        }
        if(anakarakterY<=0.0f){
            anakarakterY=0.0f
        }
        if(anakarakterY >= ekranyuksekligi-anakarakterYuksekligi) {
            anakarakterY = (ekranyuksekligi-anakarakterYuksekligi).toFloat()
        }
        karakter.y = anakarakterY
    }

    fun cisimleriHareketEttirme(){
        yesiltopX-= ekrangenisligi/49.0f
        kirmiziTopX-= ekrangenisligi/50.0f
        turuncuTopX-=ekrangenisligi/40.0f
        if(yesiltopX<0.0f){
            yesiltopX+=ekrangenisligi+20.0f
            yesilTopY= floor(Math.random()*ekranyuksekligi).toFloat()
        }
        greenball.x = yesiltopX
        greenball.y =yesilTopY

        if(kirmiziTopX<0.0f){
            kirmiziTopX+=ekrangenisligi+20.0f
            kirmiziTopY= floor(Math.random()*ekranyuksekligi).toFloat()
        }
        redball.x = kirmiziTopX
        redball.y = kirmiziTopY

        if(turuncuTopX<0.0f){
            turuncuTopX+=ekrangenisligi+20.0f
            turuncuTopY= floor(Math.random()*ekranyuksekligi).toFloat()
        }
        orangeball.x = turuncuTopX
        orangeball.y = turuncuTopY
    }
    fun carpismaKonrol(){
        val kirmiziMekezX = kirmiziTopX + redball.width/2.0f
        val kirmiziMerkezY = kirmiziTopY + redball.height/2.0f
        val turuncuMekezX = turuncuTopX + redball.width/2.0f
        val turuncuMerkezY = turuncuTopY + redball.height/2.0f
        val yesilMekezX = yesiltopX + redball.width/2.0f
        val yesilMerkezY = yesilTopY + redball.height/2.0f
        if(0.0f <=kirmiziMekezX && kirmiziMekezX <= anakarakterGenisligi
            && anakarakterY<= kirmiziMerkezY  && kirmiziMerkezY <= anakarakterY+anakarakterYuksekligi
               ){
               cansayi-=1
            can.text = cansayi.toString()
            kirmiziTopX = -10.0f

            if(cansayi == 0) {
                val intent = Intent(this, SonucEkrani::class.java)
                intent.putExtra("skor", skor)
                startActivity(intent)
                finish()
                timer.cancel()
            }
        }
        if(0.0f <=turuncuMekezX && turuncuMekezX <= anakarakterGenisligi
            && anakarakterY<= turuncuMerkezY  && turuncuMerkezY <= anakarakterY+anakarakterYuksekligi
        ){
            skor+=20

          turuncuTopX = -10.0f
        }
        if(0.0f <=yesilMekezX && yesilMekezX<= anakarakterGenisligi
            && anakarakterY<= yesilMerkezY  && yesilMerkezY <= anakarakterY+anakarakterYuksekligi
        ){
            skor+=20

            yesiltopX = -10.0f
        }
        textView.text =skor.toString()


    }
}