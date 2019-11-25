package com.example.week4day2lab

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a_main.*
import kotlinx.android.synthetic.main.fragment_b_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentA: FragmentA
    private lateinit var fragmentB: FragmentB
    var mathNumber = Math.random()
    private val requestCode = 999
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            Log.d("TAG_X", "Permission already granted")
        }
        else {
            Log.d("TAG_X", "Permission already granted")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf( Manifest
                     .permission.ACCESS_FINE_LOCATION), requestCode)
            }
        }

        RandomNumButton.setOnClickListener {
            fragmentA = FragmentA()
            fragmentB = FragmentB()

            openFragments()

        }


    }
private fun requestLocationPermission(){
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
}
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == this.requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
            ){} else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION)){
                    requestLocationPermission()
                } else {
                    Toast.makeText(
                        this,
                        "You need to allow permissions to use this application.",
                        Toast.LENGTH_SHORT
                    ).show()

                    // advise user to manually set.
                }
            }
        }
    }

    private fun openFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_one, fragmentA)
            .addToBackStack(fragmentA.tag)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_two, fragmentB)
            .addToBackStack(fragmentB.tag)
            .commit()
    }
    private fun clearfragments(){
     supportFragmentManager.beginTransaction()
         .remove(fragmentA)
         .replace(R.id.frame_layout_one, fragmentA)
         .addToBackStack(fragmentA.tag)
         .commit()
        supportFragmentManager.beginTransaction()
            .remove(fragmentB)
            .replace(R.id.frame_layout_two, fragmentB)
            .addToBackStack(fragmentB.tag)
            .commit()
    }
}
