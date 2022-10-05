package com.duran.mysolelife.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duran.mysolelife.MainActivity
import com.duran.mysolelife.R
import com.duran.mysolelife.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    private lateinit var auth: FirebaseAuth

    private val joinBtn by lazy { binding.joinBtn }
    private val emailArea by lazy { binding.emailArea }
    private val passwordArea by lazy { binding.passwordArea1 }
    private val passwordCheckArea by lazy { binding.passwordArea2 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        auth = Firebase.auth

        joinBtn.setOnClickListener {

            var isGoToJoin = true

            val email = emailArea.text.toString()
            val pwArea = passwordArea.text.toString()
            val pwCkArea = passwordCheckArea.text.toString()

            // 값이 비어있는지 확인
            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
                    isGoToJoin = false
                }
                pwArea.isEmpty() -> {
                    Toast.makeText(this, "Password를 입력해주세요", Toast.LENGTH_SHORT).show()
                    isGoToJoin = false
                }
                pwCkArea.isEmpty() -> {
                    Toast.makeText(this, "Password Check를 입력해주세요", Toast.LENGTH_SHORT).show()
                    isGoToJoin = false
                }
                !pwArea.equals(pwCkArea) -> { // password 일치 확인
                    Toast.makeText(this, "Password를 똑같이 입력해주세요", Toast.LENGTH_SHORT).show()
                    isGoToJoin = false
                }
                pwArea.length < 6 -> { // password가 6자리 이상인지
                    Toast.makeText(this, "Password를 6이상으로 입력해주세요", Toast.LENGTH_SHORT).show()
                    isGoToJoin = false
                }
                isGoToJoin -> {
                    auth.createUserWithEmailAndPassword(email, pwArea)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP // 기존 activity를 날린다.
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }

        /*auth.createUserWithEmailAndPassword("aaa@naver.com", "123123")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                }
            }*/
    }
}