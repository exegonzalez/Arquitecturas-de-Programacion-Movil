package com.example.confbeerdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.confbeerdemo.network.FirestoreService
import com.example.confbeerdemo.model.Speaker
import com.example.confbeerdemo.network.Callback

class SpeakerViewModel : ViewModel() {
    // esto se a encargar de comunicar con lo que hicimos con la UI con Firestore
    //creamos una instancia de Firebase que ya la tenemos creado

    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    fun  getSpeakerFromFirebase(){
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                proccessFinish()
            }

            override fun onFailed(exception: Exception) {
                proccessFinish()
            }
        })
    }

    fun proccessFinish(){
        isLoading.value = true
    }
}