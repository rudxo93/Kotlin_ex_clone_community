package com.duran.mysolelife

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.duran.mysolelife.auth.IntroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth

        if(auth.currentUser?.uid == null) {
            Log.d("SplashActivity", "null")
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        } else {
            Log.d("SplashActivity", "not null")
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}