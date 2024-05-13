package com.myself223.metube.ui.adapters.playlist_fragment_adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.myself223.metube.data.models.BaseMainResponse
import com.myself223.metube.data.models.ItemPlaylistDto
import com.myself223.metube.databinding.ItemPlaylistBinding
import com.myself223.metube.ui.Utils.loadImageURL
import com.myself223.metube.ui.fragments.playlist.PlaylistsFragment

class PlaylistAdapter(playlistsFragment: PlaylistsFragment, private val click: Click) : ListAdapter<ItemPlaylistDto, PlaylistAdapter.PlaylistViewHolder>(
    PlaylistDiffUtill()
) {
    class PlaylistViewHolder(private val binding: ItemPlaylistBinding) : ViewHolder(binding.root) {
        fun onBind(it:ItemPlaylistDto) {

            binding.itemTvTitle.text = it.snippetDto?.title
            binding.itemImg.loadImageURL(it.snippetDto?.descriptions?.default?.url)
            binding.itemTvCount.text = it.contentDetails?.itemCount?.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val PlaylistModel = getItem(position)
        holder.itemView.setOnClickListener {
            click.onClick(PlaylistModel)
        }
        holder.onBind(PlaylistModel)
    }

    class PlaylistDiffUtill : DiffUtil.ItemCallback<ItemPlaylistDto>() {


        override fun areItemsTheSame(oldItem: ItemPlaylistDto, newItem: ItemPlaylistDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ItemPlaylistDto,
            newItem: ItemPlaylistDto
        ): Boolean {

            return oldItem == newItem
        }
    }

    interface Click {
        fun onClick(model:ItemPlaylistDto)
    }
}
