package com.stc.postsapp.view.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.stc.domain.entity.Category
import com.stc.postsapp.R
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter  (var context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var List: MutableList<Category> = mutableListOf()
    var layoutInflater: LayoutInflater? = null
    private var clickListener: ItemClickListener? = null

    init {
        this.List = mutableListOf()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        List[position].let { holder.bindItems(it) }


    }

    fun add(data: List<Category>) {
        List.clear()
        List.addAll(data)
        notifyDataSetChanged()

    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onClick()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(element: Category) {
            itemView.tv_activity_register_mobile.text = element.strCategory
//                   add listener to set progress visible or hidden
            Glide.with(itemView.context)
                .load(element.strCategoryThumb)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(Target.SIZE_ORIGINAL)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.pb_item_category_progress_bar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.pb_item_category_progress_bar.visibility = View.GONE
                        return false
                    }
                })
                .into(itemView.iv_item_category_image)


        }
    }
}