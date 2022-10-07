package com.duran.mysolelife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duran.mysolelife.R
import com.duran.mysolelife.contentsList.BookmarkRvAdapter
import com.duran.mysolelife.contentsList.ContentModel
import com.duran.mysolelife.databinding.FragmentBookMarkBinding
import com.duran.mysolelife.databinding.FragmentTalkBinding
import com.duran.mysolelife.utils.FBAuth
import com.duran.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BookMarkFragment : Fragment() {

    private lateinit var binding: FragmentBookMarkBinding

    private val TAG = BookMarkFragment::class.java.simpleName

    private val bookmarkRv by lazy { binding.bookmarkRV }

    lateinit var rvAdapter: BookmarkRvAdapter

    private val home by lazy { binding.homeTap }
    private val tip by lazy { binding.tipTap }
    private val talk by lazy { binding.talkTap }
    private val store by lazy { binding.storeTap }

    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_mark, container, false)

        // 2. 사용자가 북마크한 정보를 다 가져온다.
        getBookmarkData()
        // 3. 전체 컨텐츠 중에서, 사용자가 북마크한 정보만 보여준다.
        rvAdapter = BookmarkRvAdapter(requireContext(), items, itemKeyList, bookmarkIdList)

        val rv : RecyclerView = bookmarkRv
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)


        home.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_homeFragment)
        }

        tip.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_tipFragment)
        }

        talk.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_talkFragment)
        }

        store.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_storeFragment)
        }

        return binding.root
    }

    private fun getCategoryData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())
                    val item = dataModel.getValue(ContentModel::class.java)

                    if(bookmarkIdList.contains(dataModel.key.toString())) {
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }
                }
                rvAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }

        }
        FBRef.category1.addValueEventListener(postListener)
        FBRef.category2.addValueEventListener(postListener)
    }

    private fun getBookmarkData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {
                    Log.e(TAG, dataModel.toString())
                    bookmarkIdList.add(dataModel.key.toString())
                }

                // 1. 전체 카테고리에 있는 컨텐츠 데이터들을 다 가져온다.
                getCategoryData()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }


        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)

    }

}