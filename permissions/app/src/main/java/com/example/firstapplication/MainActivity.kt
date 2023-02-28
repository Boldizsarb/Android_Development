package com.example.firstapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import android.Manifest
import android.content.Context
import android.location.LocationListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.location.LocationManager
import android.location.Location
import android.widget.Toast
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem


class MainActivity : AppCompatActivity(), LocationListener {

    // initiating the two variables
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main)
        val map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)
        val buttonka = findViewById<Button>(R.id.btnGo)

       // val items = ItemizedIconOverlay(this, arrayListOf<OverlayItem>(), null)

        buttonka.setOnClickListener{
                /////// Simple! geting the value of the input then pasting them in the map.
            val langtitude = findViewById<EditText>(R.id.etLattitude)
            val langtitude2 = langtitude.getText().toString().toDouble()
            val longtitude = findViewById<EditText>(R.id.etLongtitude)
            val longtitude2 = longtitude.getText().toString().toDouble()
            map1.controller.setCenter(GeoPoint(langtitude2, longtitude2))

            val markerGestureListener = object:ItemizedIconOverlay.OnItemGestureListener<OverlayItem>
            {
                override fun onItemLongPress(i: Int, item:OverlayItem ) : Boolean
                {
                    Toast.makeText(this@MainActivity, item.snippet, Toast.LENGTH_SHORT).show()
                    return true
                }

                override fun onItemSingleTapUp(i: Int, item:OverlayItem): Boolean
                {
                    Toast.makeText(this@MainActivity, item.snippet, Toast.LENGTH_SHORT).show()
                    return true
                }
            }
            val items = ItemizedIconOverlay(this, arrayListOf<OverlayItem>(), null)
            val fernhurst = OverlayItem("Fernhurst", "Village in West Sussex", GeoPoint(langtitude2,longtitude2))
            // whatever you want to add to the map just put it down here as follows:
            val home = OverlayItem("Home", "Jessie road", GeoPoint(50.79243046352737, -1.071445177879013))
            home.setMarker(ContextCompat.getDrawable(this,R.drawable.barn)) // changing the marker to a different one
            items.addItem(home)
            items.addItem(fernhurst)
            map1.overlays.add(items)

        }


        checkPermissions()
        map1.controller.setCenter(GeoPoint(latitude,longitude)) // setting it to the current address

        // calling the check permission in the main

    }
    // 1. checking for permission was granted already?, notice it is outside of the main!!
    fun checkPermissions(){ // this is my own function


        if(ContextCompat.checkSelfPermission(this,  Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            requestLocation()

        // 2.if the permission was not granted yet, request it from the user
        }else{
            // an array of permissions, note that you can request multiple at once
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),0)
        }
    }
    // 3. after the user granted or denied permisssion, the program calls this
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults) // part of the android API
        when(requestCode){ //  results 0 = Granted, results 1 = Denied
            0->{ // in case of multiple permission, the grantResults[0] is the first permission (Camera) grantResults[1] is the NFC
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        requestLocation()
                    }else{ // informing the user about the decesion
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }

    fun requestLocation(){
        // TODO open the camera later
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
        Toast.makeText(this, "Location: ${location.latitude}, ${location.longitude}", Toast.LENGTH_LONG).show()
    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText(this, "Provider disabled", Toast.LENGTH_LONG).show()
    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText(this, "Provider enabled", Toast.LENGTH_LONG).show()
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // Do nothing
    }




}