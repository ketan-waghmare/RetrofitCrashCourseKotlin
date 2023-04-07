package com.game.retrofitcrashcoursekotlin.location

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.game.retrofitcrashcoursekotlin.R
import com.game.retrofitcrashcoursekotlin.databinding.ActivityLocationBinding
import com.game.retrofitcrashcoursekotlin.models.Location
import com.game.retrofitcrashcoursekotlin.utils.NetworkResult
import com.game.retrofitcrashcoursekotlin.utils.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationActivity : AppCompatActivity() {

    private var _binding: ActivityLocationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private val locationViewModel by viewModels<LocationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locations: List<Location> = preferenceManager.getLogin().locations

        binding.apply {
            val adapter = ArrayAdapter(
                this@LocationActivity,
                android.R.layout.simple_spinner_item,
                getLocationNames(locations)
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        setSpinnerClickListener()
        setupClickListener()
        bindObservers()
    }

    private fun getLocationNames(locations: List<Location>): Array<String> {
        var locationName = Array(locations.size + 1) { "" }

        locationName[0] = "Select Location"

        for (i in locations.indices) {
            locationName[i + 1] = locations[i].name
        }

        return locationName
    }

    private fun bindObservers() {
        locationViewModel.locationResponseLiveData.observe(this) {
            when (it) {
                is NetworkResult.Error -> Toast.makeText(this, "Error Found", Toast.LENGTH_SHORT)
                    .show()
                is NetworkResult.Loading -> Toast.makeText(
                    this,
                    "Loading please wait..",
                    Toast.LENGTH_SHORT
                ).show()
                is NetworkResult.Success -> Toast.makeText(this, "success", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupClickListener() {
        binding.apply {
            btnGo.setOnClickListener {
                preferenceManager.getStringFromPref("locationId")?.let { it ->
                    locationViewModel.getLocationDetails(it)
                } ?: Toast.makeText(
                    this@LocationActivity,
                    "No Location Id Found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setSpinnerClickListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@LocationActivity,
                    "Spinner item selected",
                    Toast.LENGTH_SHORT
                ).show()

                if (position == 0) {
                    Toast.makeText(
                        this@LocationActivity,
                        "No Location selected",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.locationAddressTextview.text =
                        preferenceManager.getLogin().locations[position - 1].address
                    saveLocationId(position)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@LocationActivity,
                    "Spinner item not selected", Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun saveLocationId(position: Int) {
        Log.e("locationId", "id = ${preferenceManager.getLogin().locations[position - 1].id}")
        preferenceManager.saveString(
            "locationId",
            preferenceManager.getLogin().locations[position - 1].id
        )
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.location_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.logout -> {
                Toast.makeText(this,"click on logout",Toast.LENGTH_SHORT).show()
                callLogoutAPI()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun callLogoutAPI() {

    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}