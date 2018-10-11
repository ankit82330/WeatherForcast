package jungleworks.com.weather

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.weatherforcast.R
import com.weatherforcast.WeatherModel
import java.util.*

class WeatherAdapter(weatherList: ArrayList<WeatherModel>, mContext: Context)
    : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    private val weatherList: ArrayList<WeatherModel>
    private val mContext: Context

    init {
        this.weatherList = weatherList
        this.mContext = mContext
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.row_weather, viewGroup, false))
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, pos: Int) {
        val position = myViewHolder.adapterPosition
        val weatherObject = weatherList.get(position)

        myViewHolder.tvMaxTemp.text = "Max :" + weatherObject.tempMax.toString() + "˚C"
        myViewHolder.tvMinTemp.text = "Min :" + weatherObject.tempMin.toString() + "˚C"
        myViewHolder.tvTime.text = "at "+weatherObject.time
        Glide.with(mContext).load("https://api.openweathermap.org/img/w/"+weatherObject.icon.toString()+".png")
                .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background))
                .into(myViewHolder.ivWeather)

    }



    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivWeather: AppCompatImageView = itemView.findViewById(R.id.ivWeather)
        var tvMaxTemp: AppCompatTextView = itemView.findViewById(R.id.tvMaxTemp)
        var tvMinTemp: AppCompatTextView = itemView.findViewById(R.id.tvMinTemp)
        var tvTime: AppCompatTextView = itemView.findViewById(R.id.tvTime)
    }
}
