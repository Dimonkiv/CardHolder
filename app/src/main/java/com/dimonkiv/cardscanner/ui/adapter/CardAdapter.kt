package com.dimonkiv.cardscanner.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.utill.callback.CardCallback

class CardAdapter(private val callback: CardCallback): RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val cardList = ArrayList<BusinessCard>()

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(container.context)
                .inflate(R.layout.item_card, container, false))
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setCardList(cardList: List<BusinessCard>) {
        this.cardList.addAll(cardList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val card = cardList[pos]
        holder.bind(card)
    }

    inner class ViewHolder(private val item: View): RecyclerView.ViewHolder(item) {
        private lateinit var phoneTV: TextView
        private lateinit var emailTV: TextView
        private lateinit var nameTV: TextView
        private lateinit var iconIV: ImageView
        private lateinit var shareIV: ImageView
        private lateinit var containerLL: LinearLayout

        init {
            initUI()
        }

        private fun initUI() {
            phoneTV = item.findViewById(R.id.phone_tv)
            emailTV = item.findViewById(R.id.email_tv)
            nameTV = item.findViewById(R.id.name_tv)
            iconIV = item.findViewById(R.id.icon_iv)
            shareIV = item.findViewById(R.id.share_iv)
            containerLL = item.findViewById(R.id.container_ll)
        }

        fun bind(card: BusinessCard) {
            nameTV.text = card.name
            phoneTV.text = card.phone
            emailTV.text = card.email


            shareIV.setOnClickListener {  }

            containerLL.setOnClickListener {
                callback.onCardItemClick()}
        }

    }
}