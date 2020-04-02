package com.example.evenementerapp.ui.home.recyclerEvents

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evenementerapp.network.objects.Event
import com.example.evenementerapp.network.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class RecyclerEventsViewModel : ViewModel() {

    private val _message = MutableLiveData<String>().apply { value = "Eventos: " }
    val message: LiveData<String>
        get() = _message
    // eventos
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    init {
        var events = ArrayList<Event>()
        events.add(Event("nuevo", "description", 10,"",null,null,50,10,null))
        events.add(Event("otro", "descriptio gdfgfd f gdd dn", 10,"",null,null,50,10,null))
        events.add(Event("prueba", "descrip d fgdgfdgdfdf d fdtion", 10,"",null,null,50,10,null))
        events.add(Event("set", "descripti dff dfd fdfgdd on", 10,"",null,null,50,10,null))
        _events.value = events
    }

    fun setFragmentNumber(number: Int) {
        if (number == 1) {
            Thread {
                APIService.retrofitEventService.getAllEvents()
                    .enqueue(object : Callback<List<Event>> {
                        override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                            _message.value = "Error: " + t.toString()
                        }

                        override fun onResponse(
                            call: Call<List<Event>>,
                            response: Response<List<Event>>
                        ) {
                            val allevents = response.body()
                            Log.i("titulo: ", "dfgdfgd")
                            _events.value = allevents
                        }
                    })
            }.start()
        } else if (number == 2) {

        } else {

        }
    }

}