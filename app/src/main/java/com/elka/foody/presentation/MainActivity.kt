package com.elka.foody.presentation

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.elka.foody.R
import com.elka.foody.data.meals.MealsRepositoryImpl

class MainActivity : AppCompatActivity() {
  private var networks = mutableListOf<Network>()

  private val networkRequest by lazy {
    NetworkRequest.Builder()
      .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
      .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
      .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
      .build()
  }

  private val networkCallback by lazy {
    object : ConnectivityManager.NetworkCallback() {
      override fun onAvailable(network: Network) {
        super.onAvailable(network)
        networks.add(network)
        Log.d("networkCallback_tag", "onAvailable: ${networks.isNotEmpty()}")
      }

      override fun onLost(network: Network) {
        super.onLost(network)
        networks.remove(network)
        Log.d("networkCallback_tag", "onLost: ${networks.isNotEmpty()}")
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.requestNetwork(networkRequest, networkCallback)
  }

  override fun onStart() {
    super.onStart()

    val rep = MealsRepositoryImpl(application)


    rep.getMeals().observe(this) { val items = 1 }
    rep.loadMealsByNetwork {}
    rep.getCashedMeals().observe(this) { val items = it }
  }
}