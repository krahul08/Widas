package com.example.widas.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.widas.MainActivity
import com.example.widas.R
import com.example.widas.databinding.ListItemBinding
import com.example.widas.model.Result
import java.util.*

class RecyclerViewAdapter(var context: MainActivity) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var resultList: ArrayList<Result>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val listItemBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.list_item, viewGroup, false
        )
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val resultResponse = resultList!![i]
        viewHolder.listItemBinding.result = resultResponse
        Glide.with(context)
            .load(resultResponse.picture.thumbnail)
            .into(viewHolder.listItemBinding.imageView)

        viewHolder.listItemBinding.cardView.setOnClickListener {
            val uri = String.format(
                Locale.ENGLISH,
                "geo:%f,%f",
                resultResponse.location.coordinates.latitude.toDoubleOrNull(),
                resultResponse.location.coordinates.longitude.toDoubleOrNull()
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (resultList != null) {
            resultList!!.size
        } else {
            0
        }
    }

    fun setDataList(result: ArrayList<Result>) {
        this.resultList = result
        notifyDataSetChanged()
    }

    inner class ViewHolder(val listItemBinding: ListItemBinding) : RecyclerView.ViewHolder(
        listItemBinding.root
    )
}