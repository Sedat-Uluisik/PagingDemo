package com.sedat.pagingdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sedat.pagingdemo.databinding.ItemLayoutBinding
import com.sedat.pagingdemo.model.ImageResult
import javax.inject.Inject

class ImageAdapter @Inject constructor(
    private val glide: RequestManager
): PagingDataAdapter<ImageResult, ImageAdapter.Holder>(diffUtil()) {

    class diffUtil :DiffUtil.ItemCallback<ImageResult>(){
        override fun areItemsTheSame(oldItem: ImageResult, newItem: ImageResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageResult, newItem: ImageResult): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val imageResult = getItem(position)

        glide.load(imageResult!!.previewURL).into(holder.item.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    class Holder(val item: ItemLayoutBinding): RecyclerView.ViewHolder(item.root)

}