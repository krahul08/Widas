package com.example.widas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.widas.adapter.RecyclerViewAdapter
import com.example.widas.databinding.ActivityMainBinding
import com.example.widas.model.Result
import com.example.widas.viewmodel.ViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.swipeRefreshLayout.setOnRefreshListener {
            fetchData()
            activityMainBinding.swipeRefreshLayout.isRefreshing =
                false
        }
        val recyclerView: RecyclerView = activityMainBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        fetchData()
    }

    private fun fetchData() {
        viewModel.getAllData()?.observe(this,
            { data -> adapter.setDataList(data as ArrayList<Result>) })

    }
}