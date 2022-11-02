package com.example.weatherforecast

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val GEO_LOCATION_REQUEST_COD_SUCCESS = 1

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        checkPermission()
    }

    //-----------initial activity--------
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == GEO_LOCATION_REQUEST_COD_SUCCESS && permissions.isEmpty()){
            val intent = Intent(this, MainActivity :: class.java)
            //создание нового tack or MainActivity, а существующий tack(InitialActivity) удалить
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun checkPermission(){
        if((ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) != PackageManager.PERMISSION_GRANTED)){
            MaterialAlertDialogBuilder(this)
                .setTitle("Приложению нужны данные по геолокации")
                .setMessage("Пожалуйста, разрешите доступ к вашим гео данным для продолжения работы приложения")
                .setPositiveButton("Ок"){_,_ ->
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS)
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS)
                }
                .setNegativeButton("Закрыть"){ dialog, _ -> dialog.dismiss()}
                .create()
                .show()
        }
    }

    //-----------initial activity--------
}