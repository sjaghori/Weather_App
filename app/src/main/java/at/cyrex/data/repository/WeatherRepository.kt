package at.cyrex.data.repository;

import at.cyrex.data.api.WeatherDao

class WeatherRepository (private val weatherDao: WeatherDao) {
    suspend fun getCurrentWeatherData(app_id: String, lat: String, lon: String) =
        weatherDao.getCurrentWeatherData(app_id, lat, lon)
}
