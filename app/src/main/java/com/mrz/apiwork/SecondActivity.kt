package com.mrz.apiwork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //получение тайтла
        val descText: String? = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getString("desc")
        } else {
            savedInstanceState.getSerializable("desc") as String?
        }
        //Получение айди
        val idText: String? = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getString("id")
        } else {
            savedInstanceState.getSerializable("id") as String?
        }
        //Установка в текст элементов
        tvtitle.text = descText
        tvdesc.text = idText
    }

}