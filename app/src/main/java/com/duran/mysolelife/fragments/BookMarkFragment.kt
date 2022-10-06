package com.duran.mysolelife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.mysolelife.R
import com.duran.mysolelife.databinding.FragmentBookMarkBinding
import com.duran.mysolelife.databinding.FragmentTalkBinding

class BookMarkFragment : Fragment() {

    private lateinit var binding: FragmentBookMarkBinding

    private val home by lazy { binding.homeTap }
    private val tip by lazy { binding.tipTap }
    private val talk by lazy { binding.talkTap }
    private val store by lazy { binding.storeTap }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_mark, container, false)

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

}