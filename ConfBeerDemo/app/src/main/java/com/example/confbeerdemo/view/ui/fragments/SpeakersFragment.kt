package com.example.confbeerdemo.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.confbeerdemo.R
import com.example.confbeerdemo.model.Speaker
import com.example.confbeerdemo.view.adapter.SpeakersAdapter
import com.example.confbeerdemo.view.adapter.SpeakersListener
import com.example.confbeerdemo.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment : Fragment(), SpeakersListener {

    private  lateinit var speakerAdapter: SpeakersAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakersAdapter(this)

        rvSpeaker.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdapter
        }

        observerViewModel()

        }

        fun  observerViewModel() {
            viewModel.listSpeaker.observe(viewLifecycleOwner, Observer<List<Speaker>>{ speakers ->
                speakers.let {
                    speakerAdapter.updateData((speakers))
                }
            })

            viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
                if(it != null)
                    rlBaseSpeaker.visibility = View.INVISIBLE
            })
        }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        var bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
    }

}