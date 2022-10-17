package com.duran.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.mysolelife.R
import com.duran.mysolelife.board.BoardInsideActivity
import com.duran.mysolelife.board.BoardListLVAdapter
import com.duran.mysolelife.board.BoardModel
import com.duran.mysolelife.board.BoardWriteActivity
import com.duran.mysolelife.databinding.FragmentTalkBinding
import com.duran.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TalkFragment : Fragment() {

    private lateinit var binding: FragmentTalkBinding

    private val home by lazy { binding.homeTap }
    private val tip by lazy { binding.tipTap }
    private val bookMark by lazy { binding.bookmarkTap }
    private val store by lazy { binding.storeTap }
    private val write by lazy { binding.writeBtn }
    private val lvBoard by lazy { binding.boardListView }

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardListKey = mutableListOf<String>()

    private val TAG = TalkFragment::class.java.simpleName

    private lateinit var boardRVAdapter : BoardListLVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        boardRVAdapter = BoardListLVAdapter(boardDataList)
        lvBoard.adapter = boardRVAdapter

        // !!!!!!!
        lvBoard.setOnItemClickListener { parent, view, position, id ->
            // 첫번째 방법으로는 listview에 있는 데이터 title content time 다 다른 액티비티로 전달해줘서 만들기
            /*val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("title", boardDataList[position].title)
            intent.putExtra("content", boardDataList[position].content)
            intent.putExtra("time", boardDataList[position].time)
            startActivity(intent)*/

            // 두번째 방법으로는 Firebase에 있는 board에 대한 데이터의 id를 기반으로 다시 데이터를 받아오는 방법
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardListKey[position])
            startActivity(intent)
        }

        write.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        home.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }

        tip.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        bookMark.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookMarkFragment)
        }

        store.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardListKey.add(dataModel.key.toString())

                }

                Log.d(TAG, boardDataList.toString())

                boardListKey.reverse()
                boardDataList.reverse()
                boardRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        FBRef.boardRef.addValueEventListener(postListener)

    }

}