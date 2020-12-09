package com.example.confbeerdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.confbeerdemo.network.FirestoreService
import com.example.confbeerdemo.model.Conference
import com.example.confbeerdemo.network.Callback

class ScheduleViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    val listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object : Callback<List <Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }


}