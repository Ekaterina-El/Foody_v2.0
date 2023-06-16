package com.elka.foody.presentation

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.elka.foody.R
import com.elka.foody.data.meals.MealsRepositoryImpl
import com.elka.foody.databinding.ActivityMainBinding
import com.elka.foody.domain.meel.Meal

class MainActivity : AppCompatActivity() {
  private val viewModel by lazy { ViewModelProvider(this)[MenuViewModel::class.java] }
  private lateinit var binding: ActivityMainBinding

  private val cashedMealsObserver = Observer<List<Meal>> {
    Log.d("cashedMealsObserver", "items: $it")
  }

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
        viewModel.onRegNetwork(network)
      }

      override fun onLost(network: Network) {
        super.onLost(network)
        viewModel.onUnregNetwork(network)
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.requestNetwork(networkRequest, networkCallback)

    viewModel.cashedMeals.observe(this, cashedMealsObserver)

    setupNavigation()
  }

  private fun setupNavigation() {
    val navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    binding.bottomMenu.setupWithNavController(navController)
  }
}