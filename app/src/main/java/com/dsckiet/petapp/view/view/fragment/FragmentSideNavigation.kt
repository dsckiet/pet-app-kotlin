package com.dsckiet.petapp.view.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dsckiet.petapp.R
import com.dsckiet.petapp.databinding.FragmentSideNavBinding
import com.dsckiet.petapp.view.view.activity.MainActivity
import com.google.android.material.snackbar.Snackbar


class FragmentSideNavigation : Fragment() {

    private lateinit var binding: FragmentSideNavBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSideNavBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userPfp.setOnClickListener { }

        binding.sideNavPersonName.setText(R.string.sample_id)

        binding.closeSideNav.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.sideNavAdoption.setOnClickListener {
            findNavController().navigate(R.id.homeFragment3)
        }

        binding.sideNavCare.setOnClickListener {
            findNavController().navigate(R.id.eventFragment2)
        }

        binding.sideNavConnection.setOnClickListener {
            findNavController().navigate(R.id.forumFragment2)
        }

        binding.sideNavEvent.setOnClickListener {
            findNavController().navigate(R.id.eventFragment2)
        }

        binding.sideNavMap.setOnClickListener {
            val snackBar: Snackbar =
                Snackbar.make(it, "Layout to be created", Snackbar.LENGTH_SHORT)
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            snackBar.show()
        }

        binding.sideNavSetting.setOnClickListener {
            val snackBar: Snackbar =
                Snackbar.make(it, "Get setting layout", Snackbar.LENGTH_SHORT)
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            snackBar.show()
        }

        binding.sideNavLogout.setOnClickListener {
            val snackBar: Snackbar =
                Snackbar.make(it, "Logged out", Snackbar.LENGTH_SHORT)
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            snackBar.show()
            safeLogout()
            TODO("create logout function")
        }
    }

    private fun logout() {
        safeLogout()
    }

    private fun safeLogout() {
        val intent = Intent(this.requireContext(), MainActivity::class.java)
        startActivity(intent)
    }
}