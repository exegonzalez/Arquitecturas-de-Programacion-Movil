package com.example.tp_final_mobile.network

interface Callback <T> {
    fun onSucces(result: T?)
    fun onFail(exception: Exception)

}