package com.example.paging3practice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3practice.databinding.RecyclerMainItemBinding


class MainRecyclerViewAdapter:
    PagingDataAdapter<DownloadUrl,MainRecyclerViewAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<DownloadUrl>() {
            override fun areItemsTheSame(oldItem: DownloadUrl, newItem: DownloadUrl): Boolean {
                return oldItem.id == newItem.id && oldItem.downloadUrl == newItem.downloadUrl
            }

            override fun areContentsTheSame(oldItem: DownloadUrl, newItem: DownloadUrl): Boolean {
                return oldItem.id == newItem.id && oldItem.downloadUrl == newItem.downloadUrl
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RecyclerMainItemBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.doDataBinding(item)
    }

    inner class ViewHolder(private val binding: RecyclerMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun doDataBinding(item: DownloadUrl?) {
            with(binding) {
                binding.item = item
                this.executePendingBindings()
            }
        }
    }
}
