package com.example.evenementerapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evenementerapp.network.objects.LoginRequest
import com.example.evenementerapp.network.objects.LoginResponse
import com.example.evenementerapp.network.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    fun signIn(loginRequest: LoginRequest) {
        if (!loginRequest.userName.equals("") && !loginRequest.password.equals("")) {
            Thread {
                APIService.retrofitUserService.signIn(loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _message.value = "Error: " + t.toString()
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val loginResponse = response.body()
                        _login.value = loginResponse?.message.equals("Login Exitoso")
                        _message.value = loginResponse?.message
                    }
                })
            }.start()
        }
    }

}