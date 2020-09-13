package at.cyrex.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.cyrex.data.api.WeatherDao
import at.cyrex.data.repository.WeatherRepository
import at.cyrex.ui.viewmodel.MainViewModel

class ViewModelFactory(private val weatherDao: WeatherDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(WeatherRepository(weatherDao)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}