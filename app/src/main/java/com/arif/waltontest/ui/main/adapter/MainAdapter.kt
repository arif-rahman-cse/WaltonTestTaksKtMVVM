package com.arif.waltontest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arif.waltontest.R
import com.arif.waltontest.data.model.RpProduct
import com.arif.waltontest.databinding.ItemUserRowBinding
import com.arif.waltontest.databinding.ItemViewProductFeedbackBinding

import com.bumptech.glide.Glide


class MainAdapter(private val products: ArrayList<RpProduct>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(binding: ItemViewProductFeedbackBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding

        /*fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.userName
                textViewUserEmail.text = user.userEmail
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ItemViewProductFeedbackBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_view_product_feedback,
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.binding.tvModel.text = products[position].modelName
        holder.binding.tvColor.text = products[position].color
        Glide.with(holder.binding.appCompatImageView.context)
            .load(products[position].imageUrl)
            .into(holder.binding.appCompatImageView)

    }

    fun addProduct(products: List<RpProduct>) {
        this.products.apply {
            clear()
            addAll(products)
        }

    }
}