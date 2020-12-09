package com.example.tp_final_mobile.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.example.tp_final_mobile.R
import com.example.tp_final_mobile.model.BeeUser
import com.example.tp_final_mobile.viewmodel.BeeUserViewModel


class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var beeUserViewModel: BeeUserViewModel
    var initialLocation = LatLng(-32.0751924, -61.531025)
    lateinit var beeUsersList : ArrayList<BeeUser>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        beeUserViewModel = ViewModelProviders.of(this).get(BeeUserViewModel::class.java)
        beeUserViewModel.refresh()

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val zoom = 6f

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_bee) as BitmapDrawable }
        val smallMarker = bitmapDraw?.bitmap?.let { Bitmap.createScaledBitmap(it, 80, 80, false) }
        val markerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker)

        beeUserViewModel.listBeeUsers.observe(viewLifecycleOwner, Observer<List<BeeUser>>{ beeusers ->
            beeusers.let {
                beeUsersList = beeusers as ArrayList<BeeUser>

                for (beeuser in beeUsersList) {
                    val location = LatLng(beeuser.latitude, beeuser.longitude)

                    googleMap?.addMarker(MarkerOptions()
                        .position(location)
                        .title(beeuser.jobtitle)
                        .icon(markerIcon)
                    )
                }

                googleMap?.setOnMarkerClickListener(this)
            }
        })

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, zoom))
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        var selectedBeUser = BeeUser()
        for (beeuser in beeUsersList) {
            if (beeuser.jobtitle == p0?.title)
                selectedBeUser = beeuser
        }

        var bundle = bundleOf("beeuser" to selectedBeUser)
        findNavController().navigate(R.id.beeUserDetailDialogFragment, bundle)
        return true
    }

}