package com.example.tp_final_mobile.view.adapter

import com.example.tp_final_mobile.model.BeeUser

interface BeeUserListener {
    fun onBeeUserClicked(beeuser: BeeUser, position: Int)
}