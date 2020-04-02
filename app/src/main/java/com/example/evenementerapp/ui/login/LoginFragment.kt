package com.example.evenementerapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.evenementerapp.MainActivity
import com.example.evenementerapp.R
import com.example.evenementerapp.databinding.FragmentLoginBinding
import com.example.evenementerapp.network.objects.LoginRequest

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set Variables
        loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false)
        binding.model = loginViewModel
        // Listeners
        binding.buttonLogin.setOnClickListener {
            loginViewModel.signIn(LoginRequest(binding.etUserName.text.toString(), binding.etPassword.text.toString()))
        }
        loginViewModel.login.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        })
        loginViewModel.message.observe(this.viewLifecycleOwner, Observer {
            if (!it.equals("") && !it.equals("Login Exitoso")) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
        binding.textSignin.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        // returning
        return binding.root
    }

}
