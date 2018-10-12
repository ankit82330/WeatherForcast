package com.weatherforcast

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.weatherforcast.retrofit.APIError
import com.weatherforcast.retrofit.ResponseResolver
import com.weatherforcast.retrofit.RestClient
import com.weatherforcast.weatherData.MainAdapter
import com.weatherforcast.weatherData.WeatherData

class MainActivity : AppCompatActivity() {

    var rvMain: RecyclerView? = null
    var weatherAdapterMain: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        RestClient.getApiInterface().weatherData.enqueue(object : ResponseResolver<WeatherData>(this@MainActivity, true, false) {
            override fun success(weatherData: WeatherData) {
                Log.e("Success", "Success")

                var dateMap = LinkedHashMap<String, ArrayList<WeatherModel>>()
                for (weather in weatherData.list) {
                    var date = weather.dtTxt.split(" ")[0]
                    if (dateMap[date] == null) {
                        dateMap[date] = ArrayList<WeatherModel>()
                    }
                    val weatherList = dateMap[weather.dtTxt.split(" ")[0]]
                    val splitDate: List<String>? = weather.dtTxt.split(" ")
                    weatherList?.add(WeatherModel(weather.main.tempMin, weather.main.tempMin, weather.weather.get(0).id, splitDate!![0], splitDate!![1]
                            , weather.weather.get(0).icon))
                    dateMap[weather.dtTxt.split(" ")[0]] = weatherList!!
                }

                weatherAdapterMain = MainAdapter(dateMap, this@MainActivity)
                rvMain?.layoutManager = LinearLayoutManager(this@MainActivity,
                        LinearLayoutManager.VERTICAL, false)
                rvMain?.adapter = weatherAdapterMain
            }

            override fun failure(error: APIError) {
                Log.e("Failure", error.message)
            }
        })
    }

    private fun initializeViews() {
        rvMain = findViewById(R.id.rvMain)
    }

}
