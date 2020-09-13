package at.cyrex.data.api

class WeatherDao(private val weatherService: WeatherService) {
    suspend fun getCurrentWeatherData(app_id: String, lat: String, lon: String) =
        weatherService.getCurrentWeatherData(app_id = app_id, lat = lat, lon = lon)
}