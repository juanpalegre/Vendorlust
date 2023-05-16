package com.example.vendorlust.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vendorlust.R
import com.example.vendorlust.core.Resource
import com.example.vendorlust.data.remote.VendorDataSource
import com.example.vendorlust.databinding.FragmentVendorBinding
import com.example.vendorlust.presentation.VendorViewModel
import com.example.vendorlust.presentation.VendorViewModelFactory
import com.example.vendorlust.repository.RetrofitClient
import com.example.vendorlust.repository.VendorRepoImplement


class VendorFragment : Fragment(R.layout.fragment_vendor) {

    private lateinit var binding: FragmentVendorBinding
    private val viewModel by viewModels<VendorViewModel> { VendorViewModelFactory(VendorRepoImplement(
        VendorDataSource(RetrofitClient.webService)
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVendorBinding.bind(view)

        viewModel.fetchVendors().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading -> {
                    Log.d("liveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("liveData", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("liveData", "${result.exception}")
                }
            }
        })

    }

}