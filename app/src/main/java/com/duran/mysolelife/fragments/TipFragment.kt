package com.duran.mysolelife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.mysolelife.R
import com.duran.mysolelife.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private lateinit var binding: FragmentTipBinding

    private val home by lazy { binding.homeTap }
    private val talk by lazy { binding.talkTap }
    private val bookMark by lazy { binding.bookmarkTap }
    private val store by lazy { binding.storeTap }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

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