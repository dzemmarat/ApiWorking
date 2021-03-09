package com.mrz.apiwork.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mrz.apiwork.R
import com.mrz.apiwork.SecondActivity
import com.mrz.apiwork.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*


class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //Список всех элементов
    private var myList = emptyList<Post>()

    // Нада, но внутрянку можешь удалить, по клику на элемент вызывает тост
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, myList[position].title, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
//    Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder, и каждый дочерний view-компонент может быть найден и сохранен.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    // Количество элементов
    override fun getItemCount(): Int {
        return myList.size
    }

    //Заполнение списка и считывание кликов
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userId_txt.text = myList[position].userId.toString()
        holder.itemView.id_txt.text = myList[position].id.toString()
        holder.itemView.title_txt.text = myList[position].title
        holder.itemView.body_txt.text = myList[position].body
        //Библиотека пикассо позволяет быстро получать картинки из этих ваших тырнетиков
        Picasso.get().load("https://img2.wtftime.ru/store/2020/11/19/2a88qnr7.jpg").into(holder.itemView.ivrow); //Вот здесь будет твой элемент, за место айдишника ivrow
        //По нажатию на clrow (самому элементу списка), выполняется действие
        holder.itemView.clrow.setOnClickListener {
            //Контекст - хрень, чтоб работал интент
            val context=holder.itemView.clrow.context
            val intent = Intent( context, SecondActivity::class.java)
            //Сохранение айдишника нажатого элемента и тайтла
            intent.putExtra("id",myList[position].id.toString())
            intent.putExtra("desc", myList[position].title)
            //старт активити
            context.startActivity(intent)
        }
    }

    fun setData(newList: List<Post>) {
        myList = newList
        notifyDataSetChanged()
    }

}