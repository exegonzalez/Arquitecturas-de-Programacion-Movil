package com.example.confbeerdemo.view.ui.fragments

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.confbeerdemo.R
import com.example.confbeerdemo.model.Ubication
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24)
        toolbarUbication.setTitleTextColor(Color.WHITE)
        toolbarUbication.setNavigationOnClickListener {
            dismiss()
        }
        val ubication = Ubication()
        toolbarUbication.title = ubication.name
        tvDetailNombreLugar.text = ubication.name
        tvUbicationDirection.text = ubication.adress
        tvUbicationPhone.text = ubication.phone
        tvUbicationWebSite.text = ubication.website

        llTelefonoLugar.setOnClickListener{
            val intent = Intent(Intent(ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            })
            startActivity(intent)
        }

        llWebSite.setOnClickListener{
            val intent = Intent(Intent(ACTION_VIEW))
            intent.data = Uri.parse(ubication.website)
            startActivity(intent)

        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}