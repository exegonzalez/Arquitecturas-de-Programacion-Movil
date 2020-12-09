package com.example.confbeerdemo.view.adapter

import com.example.confbeerdemo.model.Speaker

interface SpeakersListener {
    fun onSpeakerClicked (speaker: Speaker, position: Int)
}