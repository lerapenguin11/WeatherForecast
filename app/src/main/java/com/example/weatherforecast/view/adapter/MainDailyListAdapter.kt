package com.example.weatherforecast.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R

class MainDailyListAdapter : RecyclerView.Adapter<MainDailyListAdapter.DailyHolderView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyHolderView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily, parent,false)

        return DailyHolderView(view)
    }

    override fun onBindViewHolder(holder: DailyHolderView, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class DailyHolderView(view: View) : RecyclerView.ViewHolder(view){

    }
}