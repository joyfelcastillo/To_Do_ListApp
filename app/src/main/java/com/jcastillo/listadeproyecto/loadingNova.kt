package com.jcastillo.listadeproyecto

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class loadingNova : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading_nova)


        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        },3000)
    }
}