package com.example.evenementerapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class Login : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button=view!!.findViewById<Button>(R.id.buttonSign)
        val text=view.findViewById<TextView>(R.id.textView8)

        button.setOnClickListener {

            val intent = Intent(activity, Main2Activity::class.java)

            startActivity(intent)

        }
        text.setOnClickListener {
            findNavController().navigate(R.id.action_login2_to_register)
        }
    }
}
