package com.example.vendorlust

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vendorlust.databinding.FragmentVendorBinding


class VendorFragment : Fragment(R.layout.fragment_vendor) {

    private lateinit var binding: FragmentVendorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVendorBinding.bind(view)
    }

}