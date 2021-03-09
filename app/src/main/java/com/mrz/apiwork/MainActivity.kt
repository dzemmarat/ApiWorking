package com.mrz.apiwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrz.apiwork.adapter.MyAdapter
import com.mrz.apiwork.model.Post
import com.mrz.apiwork.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Функция ниже, не трогай
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        //Кликни с ctrl на функцию getCustomPost, поймешь
        viewModel.getCustomPost(2, "id", "desc")
        //myCustomPosts - полученный список
        viewModel.myCustomPosts.observe(this, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        //Отправка данных на серв, сначала грузишь их в этот список, потом отправдяй
        val myPost = Post(2,2, "my name", "Android")
        viewModel.pushPost(myPost)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                //Проверка через консоль, если выдало код 201, значит все хорошо
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message())
            } else {
                //В случае, если все не хорошо, выдаст тост
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}