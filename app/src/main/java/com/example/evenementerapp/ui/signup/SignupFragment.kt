package com.example.evenementerapp.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.evenementerapp.MainActivity
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set variables
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentSignupBinding>(
            inflater, R.layout.fragment_signup, container, false)
        binding.model = signupViewModel
        // Listeners
        binding.buttonBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        binding.buttonSignup.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        // Returning
        return binding.root
    }
}
