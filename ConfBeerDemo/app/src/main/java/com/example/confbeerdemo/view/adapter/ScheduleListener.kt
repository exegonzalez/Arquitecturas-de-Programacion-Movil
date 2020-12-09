package com.example.confbeerdemo.view.adapter

import com.example.confbeerdemo.model.Conference

interface ScheduleListener {
    fun onConferenceClicked (conference: Conference,position: Int){

    }
}