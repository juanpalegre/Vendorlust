package com.example.vendorlust.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vendorlust.core.BaseViewHolder
import com.example.vendorlust.data.model.OpeningHours
import com.example.vendorlust.data.model.Vendor
import com.example.vendorlust.databinding.ItemVendorBinding
import java.util.*

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

    fun isVendorOpen(openingHours: OpeningHours): Boolean {
        val currentDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)

        val todayOpeningTimes = when (currentDayOfWeek) {
            Calendar.MONDAY -> openingHours.monday
            Calendar.TUESDAY -> openingHours.tuesday
            Calendar.WEDNESDAY -> openingHours.wednesday
            Calendar.THURSDAY -> openingHours.thursday
            Calendar.FRIDAY -> openingHours.friday
            Calendar.SATURDAY -> openingHours.saturday
            Calendar.SUNDAY -> openingHours.sunday
            else -> emptyList()
        }

        return todayOpeningTimes.any { openingTime ->
            val opensAt = openingTime.opensAt.split(":")
            val closesAt = openingTime.closesAt.split(":")
            val opensHour = opensAt[0].toInt()
            val opensMinute = opensAt[1].toInt()
            val closesHour = closesAt[0].toInt()
            val closesMinute = closesAt[1].toInt()

            val opensTime = Calendar.getInstance()
            opensTime.set(Calendar.HOUR_OF_DAY, opensHour)
            opensTime.set(Calendar.MINUTE, opensMinute)

            val closesTime = Calendar.getInstance()
            closesTime.set(Calendar.HOUR_OF_DAY, closesHour)
            closesTime.set(Calendar.MINUTE, closesMinute)

            opensTime.before(closesTime) &&
                    currentHour >= opensHour && currentMinute >= opensMinute &&
                    currentHour <= closesHour && currentMinute <= closesMinute
        }
    }


    private inner class VendorViewHolder(val binding: ItemVendorBinding, val context: Context): BaseViewHolder<Vendor>(binding.root){
        override fun bind(item: Vendor) {
            Glide.with(context).load(item.heroImage.url).centerCrop().into(binding.imgAvatar)
            binding.txtName.text = item.displayName
            val isOpen = isVendorOpen(item.openingHours)
            if (isOpen){
                binding.txtIsOpen.visibility = View.VISIBLE
            }
        }

    }
}