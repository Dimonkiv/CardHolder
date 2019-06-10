package com.dimonkiv.cardscanner.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image
import com.dimonkiv.cardscanner.ui.widgets.CircleIcon

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val categoryList = ArrayList<Category>()
    private val imageList = ArrayList<Image>()

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(container.context)
            .inflate(R.layout.item_category, container, false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p0: Int) {
        val pos = holder.adapterPosition
        val category = categoryList[pos]
        val image = imageList[pos]

        holder.bind(category, image)
    }

    fun setCategoryData(categoryList: List<Category>, imageList: List<Image>) {
        this.categoryList.clear()
        this.imageList.clear()
        this.categoryList.addAll(categoryList)
        this.imageList.addAll(imageList)
        notifyDataSetChanged()

    }

    inner class ViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        private lateinit var imageCI: CircleIcon
        private lateinit var titleTV: TextView

        init {
            initUI()
        }

        private fun initUI() {
            imageCI = item.findViewById(R.id.icon_CI)
            titleTV = item.findViewById(R.id.title_tv)
        }

        fun bind(category: Category, image: Image) {
            titleTV.text = category.title
            imageCI.setIconDrawable(image.imageId)
        }
    }
}