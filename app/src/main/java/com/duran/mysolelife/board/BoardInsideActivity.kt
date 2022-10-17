package com.duran.mysolelife.board

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duran.mysolelife.R
import com.duran.mysolelife.databinding.ActivityBoardInsideBinding

class BoardInsideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardInsideBinding

    private val titleArea by lazy { binding.titleArea }
    private val contentArea by lazy { binding.textArea }
    private val timeArea by lazy { binding.timeArea }

    private val TAG = BoardInsideActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)


        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()

        titleArea.text = title
        contentArea.text = content
        timeArea.text = time

        Log.d(TAG, title)
        Log.d(TAG, content)
        Log.d(TAG, time)

    }
}