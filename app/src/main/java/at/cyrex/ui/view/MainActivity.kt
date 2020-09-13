package at.cyrex.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.tooling.preview.Preview
import at.cyrex.R
import at.cyrex.data.api.ServiceBuilder
import at.cyrex.data.api.WeatherDao
import at.cyrex.data.api.model.WeatherResponse
import at.cyrex.other.Status
import at.cyrex.ui.WheaterAppTheme
import at.cyrex.ui.base.ViewModelFactory
import at.cyrex.ui.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(WeatherDao(ServiceBuilder.weatherService))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        Timber.d("setupObservers")
        viewModel.getCurrentWeatherData(getString(R.string.appid)).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Timber.d("Status.SUCCESS")
                        retrieveData(resource.data)
                    }
                    Status.ERROR -> {
                        Timber.d("Status.ERROR")
                    }
                    Status.LOADING -> {
                        Timber.d("Status.LOADING")

                    }
                }
            }
        })
    }

    private fun retrieveData(weatherResponse: WeatherResponse?) {
        setContent {
            WheaterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(weatherResponse.toString())
                }
            }
        }
    }

}

@Composable
fun Greeting(weatherResponse: String?) {

    if (weatherResponse.isNullOrEmpty()) CircularProgressIndicator()
    else {
        Text(text = "$weatherResponse!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WheaterAppTheme {
        Greeting("Android")
    }
}