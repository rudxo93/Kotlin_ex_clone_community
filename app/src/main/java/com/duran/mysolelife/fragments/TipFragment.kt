package com.duran.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.mysolelife.R
import com.duran.mysolelife.contentsList.ContentListActivity
import com.duran.mysolelife.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private lateinit var binding: FragmentTipBinding

    private val home by lazy { binding.homeTap }
    private val talk by lazy { binding.talkTap }
    private val bookMark by lazy { binding.bookmarkTap }
    private val store by lazy { binding.storeTap }

    private val category1 by lazy { binding.category1 }
    private val category2 by lazy { binding.category2 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        category1.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category1")
            startActivity(intent)
        }

        category2.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category2")
            startActivity(intent)
        }

        home.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)
        }

        talk.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)
        }

        bookMark.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_bookMarkFragment)
        }

        store.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_storeFragment)
        }

        return binding.root
    }

}