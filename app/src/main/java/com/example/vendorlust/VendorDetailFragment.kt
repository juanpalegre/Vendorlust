package com.example.vendorlust

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vendorlust.databinding.FragmentVendorDetailBinding


class VendorDetailFragment : Fragment(R.layout.fragment_vendor_detail) {

    private lateinit var binding: FragmentVendorDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVendorDetailBinding.bind(view)
    }

}