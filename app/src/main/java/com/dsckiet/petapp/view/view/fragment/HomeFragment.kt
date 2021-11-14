package com.dsckiet.petapp.view.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.petapp.R
import com.dsckiet.petapp.databinding.FragmentHomeBinding
import com.dsckiet.petapp.view.adapter.CategoriesRecyclerAdapter
import com.dsckiet.petapp.view.adapter.ProfileRecyclerAdapter
import com.dsckiet.petapp.view.data.CategoriesDataSource
import com.dsckiet.petapp.view.data.PetProfileDataSource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var profileAdapter: ProfileRecyclerAdapter
    private lateinit var categoryAdapter: CategoriesRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_recyclerView)
        //val topRecycler:RecyclerView=view.findViewById(R.id.rv_topRecyclerview)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            profileAdapter= ProfileRecyclerAdapter(requireContext())
            adapter=profileAdapter
        }
        val data2 = PetProfileDataSource.createDataSet()
        profileAdapter.submitList(data2)

        /*topRecycler.apply {
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            categoryAdapter= CategoriesRecyclerAdapter(requireContext())
            adapter=categoryAdapter

        }
        val data3 =CategoriesDataSource.createDataSet()
        categoryAdapter.submitcateg(data3)*/



        /*binding.IVLocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_userprofileFragment)
        }

        binding.ivMenu.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentsidenav)
        }*/
    }

    fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}