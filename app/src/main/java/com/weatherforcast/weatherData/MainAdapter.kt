package com.weatherforcast.weatherData

import android.content.Context
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.weatherforcast.R
import com.weatherforcast.WeatherModel
import jungleworks.com.weather.WeatherAdapter
import java.text.FieldPosition
import java.util.ArrayList

class MainAdapter(dateMap: LinkedHashMap<String, ArrayList<WeatherModel>>, mContext: Context)
    : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private val mContext: Context
    var dateMap: LinkedHashMap<String, ArrayList<WeatherModel>>
    var weatherAdapterOne: WeatherAdapter? = null

    init {
        this.mContext = mContext
        this.dateMap = dateMap
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.row_main, viewGroup, false))
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, pos: Int) {
        val position = myViewHolder.adapterPosition

        weatherAdapterOne = WeatherAdapter(getWeatherList(dateMap, myViewHolder,position), mContext)
        myViewHolder.rvInner?.adapter = weatherAdapterOne


    }

    private fun getWeatherList(dateMap: HashMap<String, ArrayList<WeatherModel>>, myViewHolder: MyViewHolder, position: Int): ArrayList<WeatherModel> {
        val l = ArrayList<ArrayList<WeatherModel>>(dateMap.values)
        var date:String?
        if (position == 0){
            date = "Today"
        } else if(position == 1){
            date = "Tomorrow"
        } else{
            date = l.get(position).get(0).date
        }
        myViewHolder.tvDate.text = date
        return l.get(position)
    }

    override fun getItemCount(): Int {
        return 5
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var rvInner: RecyclerView = itemView.findViewById(R.id.rvInner)
        val layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

        init {
            rvInner.setHasFixedSize(false);
            rvInner.setLayoutManager(layoutManager);
        }
    }
}