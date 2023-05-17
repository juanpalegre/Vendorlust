package com.example.vendorlust.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vendorlust.core.BaseViewHolder
import com.example.vendorlust.data.model.Vendor
import com.example.vendorlust.databinding.ItemVendorBinding

class VendorAdapter(
    private var vendorList: List<Vendor>,
    private val itemClickListener: OnVendorClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnVendorClickListener {
        fun onVendorClick(vendor: Vendor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemVendorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = VendorViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onVendorClick(vendorList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is VendorViewHolder -> holder.bind(vendorList[position])
        }
    }

    override fun getItemCount(): Int {
        return vendorList.size
    }

    fun updateVendorList(vendorList: List<Vendor>){
        this.vendorList = vendorList
        notifyDataSetChanged()
    }

    private inner class VendorViewHolder(val binding: ItemVendorBinding, val context: Context): BaseViewHolder<Vendor>(binding.root){
        override fun bind(item: Vendor) {
            Glide.with(context).load(item.heroImage.url).centerCrop().into(binding.imgAvatar)
            binding.txtName.text = item.displayName
        }

    }
}