package com.stc.postsapp.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.stc.domain.entity.Category
import com.stc.postsapp.R
import com.stc.postsapp.databinding.ItemCategoryBinding
import javax.inject.Inject


class CategoryAdapter @Inject constructor():PagingDataAdapter<Category,CategoryAdapter.ViewHolder>(dif) {
    lateinit var binding:ItemCategoryBinding
    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding =ItemCategoryBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(getItem(position)!!)
        holder.setIsRecyclable(false)

    }

   inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItems(element: Category) {
            binding.apply {
                tvActivityRegisterMobile.text = element.title
                Glide.with(itemView.context)
                    .load(element.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .override(Target.SIZE_ORIGINAL)
                    .listener(object : RequestListener<Drawable?> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any,
                            target: Target<Drawable?>,
                            isFirstResource: Boolean
                        ): Boolean {
                            pbItemCategoryProgressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any,
                            target: Target<Drawable?>,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            pbItemCategoryProgressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(ivItemCategoryImage)
            }
        }
    }

    companion object{
        val dif = object :DiffUtil.ItemCallback<Category>(){
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem .id== newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem== newItem
            }

        }
    }
}