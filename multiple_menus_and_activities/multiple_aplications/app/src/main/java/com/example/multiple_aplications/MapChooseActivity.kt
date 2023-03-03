package com.example.multiple_aplications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


class MapChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_choose) // layout of the second activity

        // sending value back to the main activity
        val regular = findViewById<Button>(R.id.btnRegular)
        regular.setOnClickListener {
            sendBackMapChoice(false)
        }
        val opentopomap = findViewById<Button>(R.id.btnOpenTopoMap)
        opentopomap.setOnClickListener {
            sendBackMapChoice(true)
        }

        //////// Oncreate over
    }


    fun sendBackMapChoice(opentopo: Boolean) {
        val intent = Intent()
        val bundle = bundleOf("com.example.opentopomap" to opentopo)
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()  // to forcibly finish the activity.
    }
}