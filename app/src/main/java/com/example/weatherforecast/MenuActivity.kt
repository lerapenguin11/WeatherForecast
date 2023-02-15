package com.example.weatherforecast

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.business.model.GeoCodeModel
import com.example.weatherforecast.presenters.MenuPresenter
import com.example.weatherforecast.view.adapter.CityListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.util.concurrent.TimeUnit

class MenuActivity : MvpAppCompatActivity(), MenuView {

    private val presenter by moxyPresenter { MenuPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        presenter.enable()
        presenter.getFavoriteList()
    }

    //-------------------------------- ^ menuActivity ^ -------------------------------

    //--------------------------------moxy-------------------------------
  /*  override fun setLoading(flag: Boolean) {
        loading.isActivated = flag
        loading.visibility = if(flag) View.VISIBLE else View.GONE
    }

    override fun fillPredictionList(data: List<GeoCodeModel>) {
        (predictions.adapter as CityListAdapter).updateData(data)
    }

    override fun fillFavoriteList(data: List<GeoCodeModel>) {
        (favorites.adapter as CityListAdapter).updateData(data)
    }
*/
    //--------------------------------moxy-------------------------------

    private fun initCitiList(rv: RecyclerView){
        val cityAdapter = CityListAdapter()
        cityAdapter.clickListener = searchItemClickListener
        rv.apply {
            adapter = cityAdapter
            layoutManager = object : LinearLayoutManager(this@MenuActivity, VERTICAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            setHasFixedSize(true)
        }

    }

    private val searchItemClickListener = object : CityListAdapter.SearchItemClickListener {
        override fun removeFromFavorite(item: GeoCodeModel) {
            presenter.removeLocation(item)
        }

        override fun addToFavorite(item: GeoCodeModel) {
            presenter.saveLocation(item)
        }

        override fun showWeatherIn(item: GeoCodeModel) {
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("lat",item.lat.toString())
            bundle.putString("lon",item.lon.toString())
            startActivity(intent)
        }

    }
}
