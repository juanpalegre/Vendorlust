package com.example.vendorlust.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vendorlust.R
import com.example.vendorlust.databinding.FragmentVendorDetailBinding


class VendorDetailFragment : Fragment(R.layout.fragment_vendor_detail) {

    private lateinit var binding: FragmentVendorDetailBinding
    private val args by navArgs<VendorDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVendorDetailBinding.bind(view)
        Glide.with(requireContext()).load(args.heroImageUrl).centerCrop().into(binding.vendorImage)
        binding.txtVendorName.text = args.vendorName
        binding.txtDescription.text = args.description
    }

}