package com.example.vendorlust.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.vendorlust.R
import com.example.vendorlust.databinding.FragmentVendorDetailBinding


class VendorDetailFragment : Fragment(R.layout.fragment_vendor_detail) {

    private lateinit var binding: FragmentVendorDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVendorDetailBinding.bind(view)
    }

}