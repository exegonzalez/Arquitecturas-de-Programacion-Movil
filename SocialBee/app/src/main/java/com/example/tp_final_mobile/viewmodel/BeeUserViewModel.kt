package com.example.tp_final_mobile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp_final_mobile.model.BeeUser
import com.example.tp_final_mobile.network.FirestoreService
import com.example.tp_final_mobile.network.Callback

class BeeUserViewModel: ViewModel() {

    val firestoreService = FirestoreService()
    var listBeeUsers: MutableLiveData<List<BeeUser>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getBeeUsersFromFirebase()
    }

    fun getBeeUsersFromFirebase() {
        firestoreService.getBeeUsers(object: Callback<List <BeeUser>>{
            override fun onSucces(result: List<BeeUser>?) {
                listBeeUsers.postValue(result)
                processFinished()
            }

            override fun onFail(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }
}