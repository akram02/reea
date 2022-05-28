package com.example.reea.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 * Start location update. After getting location we update the marker if map is not null
*/
@SuppressLint("MissingPermission")
fun Activity.startLocationUpdate(map: GoogleMap?) {
    val locationClient = LocationServices.getFusedLocationProviderClient(this)
    val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            val mLastLocation = result.lastLocation
            mLastLocation.let {
                val location = LatLng(mLastLocation.latitude, mLastLocation.longitude)
                if (map != null) {
                    map.addMarker(MarkerOptions().position(location).title("My Location"))
                    map.moveCamera(CameraUpdateFactory.newLatLng(location))
                }
            }
        }
    }
    locationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )
}