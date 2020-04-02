package com.example.evenementerapp.ui.login

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evenementerapp.network.objects.LoginRequest
import com.example.evenementerapp.network.objects.LoginResponse
import com.example.evenementerapp.network.services.APIService
import com.example.evenementerapp.ui.UtilsApp
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class LoginViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    @RequiresApi(Build.VERSION_CODES.O)
    fun signIn(loginRequest: LoginRequest) {
        if (!loginRequest.userName.equals("") && !loginRequest.password.equals("")) {
            loginRequest.password = UtilsApp.encryptPassword(loginRequest.password, "SecurityPasswordKey")
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