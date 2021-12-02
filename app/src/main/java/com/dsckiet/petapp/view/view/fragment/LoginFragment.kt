package com.dsckiet.petapp.view.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dsckiet.petapp.R
import com.dsckiet.petapp.databinding.FragmentLoginBinding
import com.dsckiet.petapp.view.util.Utility
import com.dsckiet.petapp.view.view.activity.HomeActivity
import com.dsckiet.petapp.view.viewmodel.ViewModel


class LoginFragment : Fragment() {

    private lateinit var auth: String
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: ViewModel
    private lateinit var utility: Utility


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        utility = Utility()

        binding.loginbtn.setOnClickListener {

            val email: String =
                binding.ettEmail.text.toString()

            val password: String =
                binding.ettPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this.context, "Please enter all credentials.", Toast.LENGTH_SHORT)
                    .show()
            } /*else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this.context, "Please enter valid email", Toast.LENGTH_SHORT).show()
            }*/ else if (utility.checkPassword(password)) {
                binding.etPassword.error = "Password incorrect."
                binding.etPassword.requestFocus()
            } else {
                binding.loginBtnText.visibility = TextView.GONE
                binding.loginProgressBar.visibility = ProgressBar.VISIBLE
                viewModel.postLogin(password, email)
                viewModel.loginData.observe(viewLifecycleOwner, {
                    if (it.isSuccessful && it.code() == 200) {
                        val intent = Intent(activity, HomeActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                        binding.loginBtnText.visibility = TextView.VISIBLE
                        binding.loginProgressBar.visibility = ProgressBar.GONE
                    } else {
                        val errorBody = it.errorBody()
                        Log.d("LoginFragment", "onViewCreated: ${errorBody.toString()}")
                        binding.loginProgressBar.visibility = ProgressBar.GONE
                        binding.loginBtnText.visibility = TextView.VISIBLE
                    }
                })

            }
        }

        binding.goToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signup2Fragment)
        }
    }
}