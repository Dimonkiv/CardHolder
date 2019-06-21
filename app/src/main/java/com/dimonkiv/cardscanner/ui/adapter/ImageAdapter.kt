package com.dimonkiv.cardscanner.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.Image
import com.dimonkiv.cardscanner.ui.widgets.CircleIcon
import java.util.*
import kotlin.collections.ArrayList

class ImageAdapter(private val callback: Callback) : RecyclerView.Adapter<ImageAdapter.ViewHolder>()  {
    private val imageList = ArrayList<Image>()
    private val isSelectedItemList = ArrayList<Boolean>()
    private var prevItemPos: Int? = null

    interface Callback {
        fun onSelectItem(imageId: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = imageList[pos]
        val isSelected = isSelectedItemList[pos]

        holder.bind(item, isSelected)
    }

    fun setItems(imageList: List<Image>) {
        this.imageList.clear()
        this.imageList.addAll(imageList)
        initIsSelectedItemList()
        notifyDataSetChanged()
    }

    fun unselectItem(pos: Int?) {
        if (pos != null) {
            isSelectedItemList[pos] = false
            notifyItemChanged(pos)
        }
    }

    private fun initIsSelectedItemList() {
        for (it in imageList) {
            isSelectedItemList.add(false)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var iconCI: CircleIcon = itemView.findViewById(R.id.icon_ci)
        private var selectContainerV: View = itemView.findViewById(R.id.select_container)

        fun bind(item: Image, isSelected: Boolean) {
            iconCI.setIconDrawable(item.imageId)

            if (!isSelected) {
                setNotSelectedItemMode()
            }


            iconCI.setOnClickListener {
                callback.onSelectItem(item.id)
                onSelectItem()
            }
        }

        private fun onSelectItem() {
            unselectItem(prevItemPos)
            prevItemPos = adapterPosition
            setSelectedItemMode()
        }

        private fun setSelectedItemMode() {
            selectContainerV.visibility = View.VISIBLE
        }

        private fun setNotSelectedItemMode() {
            selectContainerV.visibility = View.GONE
        }
    }
}