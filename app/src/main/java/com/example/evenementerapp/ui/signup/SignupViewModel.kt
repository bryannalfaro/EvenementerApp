package com.example.evenementerapp.ui.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evenementerapp.network.objects.User
import com.example.evenementerapp.network.objects.UserSingleResponse
import com.example.evenementerapp.network.services.APIService
import com.example.evenementerapp.ui.UtilsApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _signup = MutableLiveData<Boolean>()
    val signup: LiveData<Boolean>
        get() = _signup

    @RequiresApi(Build.VERSION_CODES.O)
    fun signUp(user: User) {
        if (!user.userName.equals("") && !user.password.equals("") &&
            !user.Name.equals("") && !user.LastName.equals("") &&
            !user.Email.equals("")) {
            user.password = UtilsApp.encryptPassword(user.password, "SecurityPasswordKey")
            Thread {
                APIService.retrofitUserService.signUp(user).enqueue(object : Callback<UserSingleResponse> {

                    override fun onFailure(call: Call<UserSingleResponse>, t: Throwable) {
                        _message.value = "Error: " + t.toString()
                    }

                    override fun onResponse(
                        call: Call<UserSingleResponse>,
                        response: Response<UserSingleResponse>
                    ) {
                        val loginResponse = response.body()
                        _signup.value = !loginResponse?.user.isNullOrEmpty()
                        if (!signup.value!!) {
                            _message.value = "El usuario ya existe"
                        }
                    }
                })
            }.start()
        } else {
            _message.value = "Complete todos los campos"
        }
    }
}