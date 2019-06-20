package com.dimonkiv.cardscanner.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.db.dao.RecognizedText
import com.dimonkiv.cardscanner.utill.CardFieldTag

class CardRecognizedAdapter(private val context: Context,
                            private val callback: Callback): RecyclerView.Adapter<CardRecognizedAdapter.ViewHolder>() {

    interface Callback {
        fun onFieldTagSelected(item: RecognizedText)
    }

    private val items = ArrayList<RecognizedText>()

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(container.context)
                .inflate(R.layout.item_recognized_text, container, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = items[pos]
        holder.bind(item)
    }

    fun setItems(items: List<RecognizedText>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private lateinit var titleTV: TextView
        private lateinit var doneIB: ImageButton

        init {
            initUI()
        }

        private fun initUI() {
            titleTV  = itemView.findViewById(R.id.title_tv)
            doneIB = itemView.findViewById(R.id.done_ib)
        }

        fun bind(item: RecognizedText) {
            titleTV.text = item.title

            setDoneType(item)

            doneIB.setOnClickListener {
                showPopupMenu(item)
            }
        }

        private fun setDoneType(item: RecognizedText) {
            if (item.tag == CardFieldTag.UNKNOWN) {
                return
            }

            doneIB.setImageResource(R.drawable.ic_done_approved)
        }

        private fun showPopupMenu(item: RecognizedText) {
            val popupMenu = PopupMenu(context, doneIB)
            popupMenu.inflate(R.menu.menu_card_fields)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.name -> item.tag = CardFieldTag.TITLE

                    R.id.address -> item.tag = CardFieldTag.ADDRESS

                    R.id.email -> item.tag = CardFieldTag.EMEIL

                    R.id.phone -> item.tag = CardFieldTag.PHONE

                    R.id.site -> item.tag = CardFieldTag.SITE
                }

                setDoneType(item)
                callback.onFieldTagSelected(item)
                true
            }
            popupMenu.show()
        }
    }
}