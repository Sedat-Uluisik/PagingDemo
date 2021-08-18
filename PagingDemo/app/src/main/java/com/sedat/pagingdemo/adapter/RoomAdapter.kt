package com.sedat.pagingdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sedat.pagingdemo.databinding.RoomItemLayoutBinding
import com.sedat.pagingdemo.model.myClass
import javax.inject.Inject

class RoomAdapter @Inject constructor(): PagingDataAdapter<myClass, RoomAdapter.Holder>(diffUtil()) {

    class diffUtil: DiffUtil.ItemCallback<myClass>(){
        override fun areItemsTheSame(oldItem: myClass, newItem: myClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: myClass, newItem: myClass): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: RoomAdapter.Holder, position: Int) {
        val myClass = getItem(position)

        if(myClass != null)
            holder.item.myText.text = myClass.number.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.Holder {
        val binding = RoomItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    class Holder(val item: RoomItemLayoutBinding): RecyclerView.ViewHolder(item.root)

}