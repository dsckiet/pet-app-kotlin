package com.dsckiet.petapp.view.view.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dsckiet.petapp.R
import com.dsckiet.petapp.databinding.FragmentUserprofileBinding

class UserprofileFragment : Fragment() {

    private lateinit var binding: FragmentUserprofileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserprofileBinding.inflate(layoutInflater)

        activity?.window?.statusBarColor = Color.GRAY
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userBack.setOnClickListener {
            findNavController().navigate(R.id.action_userprofileFragment_to_homeFragment3)
        }
    }

}