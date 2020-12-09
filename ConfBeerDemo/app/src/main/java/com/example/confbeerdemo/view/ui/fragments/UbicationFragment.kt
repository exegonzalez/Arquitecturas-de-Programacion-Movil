package com.example.confbeerdemo.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.core.content.ContextCompat
import com.example.confbeerdemo.R
import com.example.confbeerdemo.model.Ubication
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment= childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication  = Ubication()
        val zoom = 16f
        val centerMap = LatLng(ubication.latitude, ubication.longitude)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark= LatLng(ubication.latitude, ubication.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title("BeerConf Lagash 2020")

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_lagash) as BitmapDrawable}
        val smallMarker = bitmapDraw?.bitmap?.let { Bitmap.createScaledBitmap(it, 150, 150, false) }
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))

        googleMap?.addMarker(markerOptions)
        googleMap?.setOnMarkerClickListener(this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }


}