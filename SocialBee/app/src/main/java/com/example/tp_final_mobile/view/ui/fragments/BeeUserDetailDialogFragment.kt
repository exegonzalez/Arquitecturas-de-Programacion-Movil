package com.example.tp_final_mobile.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tp_final_mobile.R
import com.example.tp_final_mobile.model.BeeUser
import kotlinx.android.synthetic.main.fragment_bee_user_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

class BeeUserDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bee_user_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBeeUser.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_white)
        toolbarBeeUser.setTitleTextColor(Color.WHITE)
        toolbarBeeUser.setNavigationOnClickListener {
            dismiss()
        }

        val beeuser = arguments?.getSerializable("beeuser") as BeeUser

        tvDetailBeeUserJobtitle.text = beeuser.jobtitle
        tvDetailBeeUserName.text = beeuser.name
        tvDetailBeeUserBiography.text = beeuser.biography
        tvDetailBeeUserEmail.text = beeuser.email
        tvDetailBeeUserAddress.text = beeuser.address

        ivBeeUserPhone.setOnClickListener {
            val intent = Intent(Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: ${beeuser.phone}")
            })
            startActivity(intent)
        }

        ivBeeUserWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(beeuser.web))
            startActivity(intent)
        }

        ivBeeUserMessage.setOnClickListener {
            val intent = Intent(Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto: ${beeuser.phone}")
            })
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}