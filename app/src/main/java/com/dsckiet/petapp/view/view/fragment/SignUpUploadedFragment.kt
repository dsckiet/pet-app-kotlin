package com.dsckiet.petapp.view.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dsckiet.petapp.R
import com.dsckiet.petapp.databinding.FragmentSignUp6Binding
import com.dsckiet.petapp.view.model.PostOwnerData
import com.dsckiet.petapp.view.model.PostRegister
import com.dsckiet.petapp.view.view.activity.HomeActivity
import com.dsckiet.petapp.view.viewmodel.ViewModel


class SignUpUploadedFragment : Fragment() {

    private lateinit var binding: FragmentSignUp6Binding
    private val args: SignUpUploadedFragmentArgs by navArgs()
    private lateinit var register: PostRegister
    private lateinit var viewModel: ViewModel
    private val TAG = "uploaded"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up6, container, false)
        args.postOwnerData

        Log.d(TAG, "onCreateView: ${args.postOwnerData}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.finishbtn.setOnClickListener {

            val data = args.postOwnerData

            viewModel.postRegister(
                PostOwnerData(
                    breed = data.breed,
                    category = data.category,
                    description = data.description,
                    email = data.email,
                    gender = data.gender,
                    name = data.name,
                    password = data.password,
                    petName = data.petName,
                    username = data.username
                )
            )


            Log.d(TAG, "onViewCreated: ${petDetails()}")

            viewModel.registerData.observe(viewLifecycleOwner, {
                Log.d(TAG, "onViewCreated: $it")
                Log.d(TAG, "onViewCreated: Observing")
                if (it.isSuccessful) {
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    val error = it.errorBody()
                    findNavController().navigate(R.id.loginFragment)
                    Toast.makeText(context, "Signup Failed", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onViewCreated: ${it.errorBody()}")
                    Toast.makeText(context, "${error.toString()}", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onViewCreated: ${error.toString()}")
                }
            })

        }
    }

    private fun petDetails(): PostOwnerData {
        val data = args.postOwnerData

        return PostOwnerData(
            breed = data.breed,
            category = data.category,
            description = data.description,
            email = data.email,
            gender = data.gender,
            name = data.name,
            password = data.password,
            petName = data.petName,
            username = data.username
        )
    }

}