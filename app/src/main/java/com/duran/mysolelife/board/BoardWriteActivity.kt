package com.duran.mysolelife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.duran.mysolelife.R
import com.duran.mysolelife.contentsList.BookmarkModel
import com.duran.mysolelife.databinding.ActivityBoardWriteBinding
import com.duran.mysolelife.utils.FBAuth
import com.duran.mysolelife.utils.FBRef

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding

    private val TAG = BoardWriteActivity::class.java.simpleName

    private val btnWrite by lazy { binding.writeBtn }
    private val evTitle by lazy { binding.titleArea }
    private val evContent by lazy { binding.contentArea }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        btnWrite.setOnClickListener {
            val title = evTitle.text.toString()
            val content = evContent.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            Log.d(TAG, title)
            Log.d(TAG, content)

            // board
            //      - key
            //          -boardModel(title, content, uid, time)
            FBRef.boardRef
                .push()
                .setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_SHORT).show()

            finish()

        }

    }
}