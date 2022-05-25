package com.example.akharinkhabar.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
class AdapterMain() :
    RecyclerView.Adapter<AdapterMain.ViewHolder>() {
    lateinit var wideBinding: ItemWideBinding
    lateinit var simpleBinding: ItemSimpleBinding
    lateinit var videoBinding: ItemVideoBinding

    private var models = mutableListOf<RelationMain>()


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

        fun bind() {
            when {
                models[adapterPosition].simple?.serverId != 0L && models[adapterPosition].simple?.serverId != null -> {
                    simpleBinding.simple = models[adapterPosition].simple
                }
                models[adapterPosition].video?.serverId != 0L && models[adapterPosition].video?.serverId != null -> {
                    videoBinding.video = models[adapterPosition].video

                }
                models[adapterPosition].wide?.serverId != 0L && models[adapterPosition].wide?.serverId != null -> {
                    wideBinding.wide = models[adapterPosition].wide
                }
                else -> {
                    videoBinding.video = models[adapterPosition].video
                }
            }
        }
    }

    private val typeVideo = 0
    private val typeWide = 1
    private val typeSimple = 2

    override fun getItemViewType(position: Int): Int {
        return when {
            models[position].simple?.serverId != 0L && models[position].simple?.serverId != null -> {
                typeSimple
            }
            models[position].video?.serverId != 0L && models[position].video?.serverId != null -> {
                typeVideo
            }
            models[position].wide?.serverId != 0L && models[position].wide?.serverId != null -> {
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

    fun setList(list: List<RelationMain>) {
            models.addAll(list)
            this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = models.size


}