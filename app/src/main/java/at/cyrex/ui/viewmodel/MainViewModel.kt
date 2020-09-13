package at.cyrex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import at.cyrex.data.repository.WeatherRepository
import at.cyrex.other.Constants.LATITUDE
import at.cyrex.other.Constants.LONGITUDE
import at.cyrex.other.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getCurrentWeatherData(key: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = weatherRepository.getCurrentWeatherData(
                        app_id = key,
                        lat = LATITUDE,
                        lon = LONGITUDE
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}