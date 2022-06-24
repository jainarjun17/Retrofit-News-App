package com.example.cheezyretrofitnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.StackView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView

    lateinit var adapter:MyAdapter

    var newsList = mutableListOf<Article>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.news_list)

        adapter=MyAdapter(this@MainActivity,newsList)
        recyclerView.adapter=adapter
        //recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)

       //val layoutManager

        getNews()

    }

    private fun getNews() {
        val news = NewsServices.newsInterface.getHeadLines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("CheezyCode", news.toString())
                    newsList.addAll(news.articles)
                    adapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("CheezyCode", "Error displaying news", t)
            }

        })
    }


}