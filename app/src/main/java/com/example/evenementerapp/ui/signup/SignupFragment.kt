package com.example.evenementerapp.ui.signup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.evenementerapp.MainActivity
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentSignupBinding
import com.example.evenementerapp.network.objects.User


class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    @RequiresApi(Build.VERSION_CODES.O)
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
            signupViewModel.signUp(User(binding.etUserName.text.toString(), binding.etPassword.text.toString(),
                                        binding.etName.text.toString(), binding.etLastName.text.toString(),
                                        binding.etEmail.text.toString(), ""))
        }
        signupViewModel.signup.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        })
        signupViewModel.message.observe(this.viewLifecycleOwner, Observer {
            if (!it.equals("") && !it.equals("Login Exitoso")) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
        // Returning
        return binding.root
    }
}
