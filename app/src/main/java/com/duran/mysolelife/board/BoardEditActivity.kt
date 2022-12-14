package com.duran.mysolelife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.duran.mysolelife.R
import com.duran.mysolelife.databinding.ActivityBoardEditBinding
import com.duran.mysolelife.utils.FBAuth
import com.duran.mysolelife.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception

class BoardEditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardEditBinding

    private val titleArea by lazy { binding.titleArea }
    private val contentArea by lazy { binding.contentArea }
    private val imageArea by lazy { binding.imageArea }
    private val btnEdit by lazy { binding.editBtn }

    private val TAG = BoardEditActivity::class.java.simpleName

    private lateinit var writerUid : String

    private lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        key = intent.getStringExtra("key").toString()

        getBoardData(key)
        getImageData(key)
        btnEdit.setOnClickListener {
            editBoardData(key)
        }
    }

    private fun editBoardData(key : String) {

        FBRef.boardRef
            .child(key)
            .setValue(
                BoardModel(titleArea.text.toString(),
                            contentArea.text.toString(),
                            writerUid,
                            FBAuth.getTime())
            )

        Toast.makeText(this, "수정완료", Toast.LENGTH_SHORT).show()
        finish()

    }

    private fun getImageData(key: String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageViewFromFB = imageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)

            } else {

            }
        })
    }

    private fun getBoardData(key: String) {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                /*Log.d(TAG, dataModel.toString())
                Log.d(TAG, dataModel!!.title!!)
                Log.d(TAG, dataModel.time)*/

                titleArea.setText(dataModel?.title)
                contentArea.setText(dataModel?.content)
                writerUid = dataModel!!.uid.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}