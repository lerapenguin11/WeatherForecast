package com.example.weatherforecast.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.weatherforecast.R
import com.example.weatherforecast.business.model.HourlyWeatherModel
import com.google.android.material.textview.MaterialTextView

class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : HourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly, parent, false)

        return HourlyViewHolder(view)
    }

    inner class HourlyViewHolder(view : View) : BaseViewHolder(view) {

        @BindView(R.id.item_hourly_time_tv)
        lateinit var time : MaterialTextView

        @BindView(R.id.item_hourly_temp_tv)
        lateinit var temperature : MaterialTextView

        @BindView(R.id.item_hourly_pop_tv)
        lateinit var popBate : MaterialTextView

        @BindView(R.id.item_hourly_weather_condition_icon)
        lateinit var icon : ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {
            time.text = "18:00"
            temperature.text = "16\u00B0"
            popBate.text = "14 %"
            icon.setImageResource(R.drawable.ic_sun)
        }

    }
}