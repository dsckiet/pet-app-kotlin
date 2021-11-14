package com.dsckiet.petapp.view.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dsckiet.petapp.R

import com.google.android.material.bottomnavigation.BottomNavigationView




class ParticularChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_particular_chat, container, false)
    }
}