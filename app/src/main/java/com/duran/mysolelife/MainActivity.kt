package com.duran.mysolelife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.mysolelife.auth.IntroActivity
import com.duran.mysolelife.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    /*private val logoutBtn by lazy { binding.logoutBtn }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        auth = Firebase.auth

        /*logoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP // 기존 activity를 날린다.
            startActivity(intent)
        }*/
    }
}