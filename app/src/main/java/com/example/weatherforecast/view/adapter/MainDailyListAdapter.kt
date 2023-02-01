package com.example.weatherforecast.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.weatherforecast.R
import com.example.weatherforecast.business.model.DailyWeatherModel
import com.google.android.material.textview.MaterialTextView

class MainDailyListAdapter : BaseAdapter<DailyWeatherModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyHolderView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily, parent,false)

        return DailyHolderView(view)
    }

    @SuppressLint("NonConstantResourceId")
    inner class DailyHolderView(view: View) : BaseViewHolder(view){

        @BindView(R.id.item_daily_data_time)
        lateinit var dailyTimeData : MaterialTextView

        @BindView(R.id.item_daily_weather_condition_icon)
        lateinit var icon : ImageView

        @BindView(R.id.item_daily_pop_tv)
        lateinit var dailyPop : MaterialTextView

        @BindView(R.id.item_daily_max_temp_tv)
        lateinit var dailyMaxTemp : MaterialTextView

        @BindView(R.id.item_daily_min_temp_tv)
        lateinit var dailyMinTemp : MaterialTextView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {
            mData[position].apply {
                dailyTimeData.text = "20 Saturday"
                icon.setImageResource(R.drawable.ic_sun)
                dailyPop.text = "25 %"
                dailyMaxTemp.text = "26\u00B0"
                dailyMinTemp.text = "19\u00B0"
            }
        }

    }
}