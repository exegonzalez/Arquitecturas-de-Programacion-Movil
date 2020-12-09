package com.example.confbeerdemo.view.ui.fragments

import android.app.VoiceInteractor
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.confbeerdemo.R
import com.example.confbeerdemo.model.Speaker
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import java.text.SimpleDateFormat

class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24)
        toolbarSpeaker.setTitleTextColor((Color.WHITE))
        toolbarSpeaker.setNavigationOnClickListener{
            dismiss()
        }

        val speaker = arguments?.getSerializable("speaker") as Speaker
        toolbarSpeaker.title = speaker.name
        tvDetailSpeakerName.text = speaker.name
        tvDetailSpeakerBiography.text = speaker.biography
        tvDetailSpeakerJobtitle.text = speaker.jobtitle
        tvDetailSpeakerWorkplace.text = speaker.workplace
        Glide.with(this)
            .load((speaker.image))
            .apply(RequestOptions.circleCropTransform())
            .into(ivDetailSpeakerImage)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}