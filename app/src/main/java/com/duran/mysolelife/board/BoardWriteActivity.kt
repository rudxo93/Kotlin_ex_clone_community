package com.duran.mysolelife.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.duran.mysolelife.R
import com.duran.mysolelife.contentsList.BookmarkModel
import com.duran.mysolelife.databinding.ActivityBoardWriteBinding
import com.duran.mysolelife.utils.FBAuth
import com.duran.mysolelife.utils.FBRef
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding

    private val TAG = BoardWriteActivity::class.java.simpleName

    private val btnWrite by lazy { binding.writeBtn }
    private val evTitle by lazy { binding.titleArea }
    private val evContent by lazy { binding.contentArea }
    private val imageArea by lazy { binding.imageArea }

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

            /*
                파이어베이스 store에 이미지를 저장하고 싶습니다.
                만약에 내가 게시글을 클릭했을 때, 게시글에 대한 정보를 받아와야 하는데
                이미지 이름에 대한 정보를 모르기 때문에
                이미지 이름을 문서의 key값으로 해줘서 이미지에 대한 정보를 찾기 쉽게 한다.
            */
            val key = FBRef.boardRef.push().key.toString() // 키값부터 미리 생성

            FBRef.boardRef
                .child(key)
                .setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_SHORT).show()

            imageUpload(key)

            finish()

        }

        imageArea.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100) {
            imageArea.setImageURI(data?.data)
        }
    }

    private fun imageUpload(key : String) {

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(key + ".png")

        // Get the data from an ImageView as bytes
        imageArea.isDrawingCacheEnabled = true
        imageArea.buildDrawingCache()
        val bitmap = (imageArea.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

}