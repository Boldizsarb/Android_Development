package com.example.multiple_aplications


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
import android.content.Intent
import android.location.LocationListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.location.LocationManager
import android.location.Location
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem


 class AddPremise :  AppCompatActivity() {

    lateinit var map1: MapView // this way the map1 is available everywhere!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_premise_layout)



        map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)
        val buttonka = findViewById<Button>(R.id.btnGo)
        buttonka.setOnClickListener {
            /////// Simple! geting the value of the input then pasting them in the map.
            val langtitude = findViewById<EditText>(R.id.etLattitude)
            val langtitude2 = langtitude.getText().toString().toDouble()
            val longtitude = findViewById<EditText>(R.id.etLongtitude)
            val longtitude2 = longtitude.getText().toString().toDouble()
            map1.controller.setCenter(GeoPoint(langtitude2, longtitude2))
        }

    }
     // had to include all of these tohervise the menu would not work
     val mapChooseLauncher =  // the second activity
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
             // The lambda function starts here (like an arrow function)
             // Check that we get an OK (success) result from the second activity
             if (it.resultCode == RESULT_OK) {
                 it.data?.apply {
                     val opentopomap = this.getBooleanExtra("com.example.opentopomap", false) // false is a default value

                     // See "lateinit" in week 6 for a way of doing this more efficiently
                     val map1 = findViewById<MapView>(R.id.map1)
                     map1.setTileSource( if (opentopomap) TileSourceFactory.OpenTopo else TileSourceFactory.MAPNIK )
                 }
             }
             // The lambda function ends here
         }
     val addPremiseLauncher =  // third menu point
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
             val returnIntent : Intent? = it.data // gives us the intent, that was sent back from the second activity

             if(it.resultCode == RESULT_OK){
                 returnIntent.apply { // only runing variable if the variable (returnIntent) is not null
                     // within this wont be null so no need for null checking
                 }
             }
         }

     override fun onCreateOptionsMenu(menu: Menu) : Boolean {  // inflating the menu
         // In Kotlin, we can just use 'menuInflater' instead (see the notes on
         // Kotlin properties in week 2)
         menuInflater.inflate(R.menu.menu, menu)
         return true      // indication if succescful
     }

     override fun onOptionsItemSelected(item: MenuItem) : Boolean { // when the user selects a menu item
         when(item.itemId) {  // user selected the choosemap id
             R.id.choosemap -> {
                 val intent = Intent(this,MapChooseActivity::class.java)
                 mapChooseLauncher.launch(intent)

                 return true
             }
         }
         when(item.itemId){
             R.id.addPremise ->{
                 val intent = Intent(this,AddPremise::class.java)  // the activity file
                 addPremiseLauncher.launch(intent)
                 return true
             }
         }
         return false
     }

}