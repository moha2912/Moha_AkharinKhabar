package com.example.akharinkhabar.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.akharinkhabar.R
import com.example.akharinkhabar.data.model.LatestNew
import com.example.akharinkhabar.data.model.db.RelationMain
import com.example.akharinkhabar.databinding.ItemSimpleBinding
import com.example.akharinkhabar.databinding.ItemVideoBinding
import com.example.akharinkhabar.databinding.ItemWideBinding

/**
 * Created by moha on 2022-05-21.
 */
class AdapterMain :
    ListAdapter<RelationMain, AdapterMain.ViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RelationMain>() {
            override fun areItemsTheSame(oldItem: RelationMain, newItem: RelationMain): Boolean {
                return oldItem.latestNewsItem.id == newItem.latestNewsItem.id
            }

            override fun areContentsTheSame(oldItem: RelationMain, newItem: RelationMain): Boolean {
                return oldItem == newItem
            }
        }
    }

    lateinit var wideBinding: ItemWideBinding
    lateinit var simpleBinding: ItemSimpleBinding
    lateinit var videoBinding: ItemVideoBinding



    inner class ViewHolder : RecyclerView.ViewHolder {
        lateinit var wideBinding: ItemWideBinding
        lateinit var simpleBinding: ItemSimpleBinding
        lateinit var videoBinding: ItemVideoBinding

        constructor(b: ItemWideBinding) : super(b.root) {
            wideBinding = b
        }

        constructor(b: ItemSimpleBinding) : super(b.root) {
            simpleBinding = b
        }

        constructor(b: ItemVideoBinding) : super(b.root) {
            videoBinding = b
        }

        fun bind(item: RelationMain) {
            when {
                item.simple?.serverId != 0L && item.simple?.serverId != null -> {
                    simpleBinding.simple = item.simple
                }
                item.video?.serverId != 0L && item.video?.serverId != null -> {
                    videoBinding.video = item.video

                }
                item.wide?.serverId != 0L && item.wide?.serverId != null -> {
                    wideBinding.wide = item.wide
                }
                else -> {
                    videoBinding.video = item.video
                }
            }
        }
    }

    private val typeVideo = 0
    private val typeWide = 1
    private val typeSimple = 2

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position).simple?.serverId != 0L && getItem(position).simple?.serverId != null -> {
                typeSimple
            }
            getItem(position).video?.serverId != 0L && getItem(position).video?.serverId != null -> {
                typeVideo
            }
            getItem(position).wide?.serverId != 0L && getItem(position).wide?.serverId != null -> {
                typeWide
            }
            else -> {
                typeVideo
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            typeSimple -> {
                simpleBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_simple,
                    parent,
                    false
                )
                return ViewHolder(simpleBinding)
            }
            typeVideo -> {
                videoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_video,
                    parent,
                    false
                )
                return ViewHolder(videoBinding)
            }
            else -> {
                wideBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_wide,
                    parent,
                    false
                )
                return ViewHolder(wideBinding)
            }

        }
    }

//    fun setList(list: List<RelationMain>) {
//        models.addAll(list)
//        this.notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}