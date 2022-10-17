package com.duran.mysolelife.board

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duran.mysolelife.R
import com.duran.mysolelife.databinding.ActivityBoardInsideBinding
import com.duran.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BoardInsideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardInsideBinding

    private val titleArea by lazy { binding.titleArea }
    private val contentArea by lazy { binding.textArea }
    private val timeArea by lazy { binding.timeArea }

    private val TAG = BoardInsideActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        // 첫번째 방법
        /*val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()

        titleArea.text = title
        contentArea.text = content
        timeArea.text = time*/

        // 두번째 방법
        val key = intent.getStringExtra("key")

        Toast.makeText(this, key, Toast.LENGTH_SHORT).show()

        getBoardData(key.toString())

    }

    private fun getBoardData(key : String) {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                /*Log.d(TAG, dataSnapshot.toString())*/
                val dataModel =  dataSnapshot.getValue(BoardModel::class.java)
                /*Log.d(TAG, dataModel!!.title!!)*/

                titleArea.text = dataModel!!.title
                contentArea.text = dataModel.content
                timeArea.text = dataModel.time

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)

    }
}