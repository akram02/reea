package com.example.reea.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter(@LayoutRes private val resource: Int) : RecyclerView.Adapter<EmptyHolder>() {

    private var items: MutableList<Any> = ArrayList()
    var clickListener: ClickListener = object:ClickListener{override fun onItemClick(item:View,any:Any){} }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            EmptyHolder(LayoutInflater.from(parent.context).inflate(resource, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EmptyHolder, position: Int) {
        bindView(holder.itemView, items[position], position)
    }

    abstract fun bindView(view: View, any: Any, position: Int=0)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(itemList: MutableList<Any>) {
        items = itemList
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ClickListener) {
        this.clickListener = itemClickListener
    }
}