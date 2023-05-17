package com.example.vendorlust.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.vendorlust.R
import com.example.vendorlust.core.Resource
import com.example.vendorlust.data.model.Vendor
import com.example.vendorlust.data.remote.VendorDataSource
import com.example.vendorlust.databinding.FragmentVendorBinding
import com.example.vendorlust.presentation.VendorViewModel
import com.example.vendorlust.presentation.VendorViewModelFactory
import com.example.vendorlust.repository.RetrofitClient
import com.example.vendorlust.repository.VendorRepoImplement
import com.example.vendorlust.ui.adapters.VendorAdapter


class VendorFragment : Fragment(R.layout.fragment_vendor), VendorAdapter.OnVendorClickListener {

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
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("livedata", "Loading...")
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerMain.adapter = VendorAdapter(result.data, this@VendorFragment)
                    binding.etSearch.addTextChangedListener { userFilter ->
                        val vendorsFiltered = result.data.filter { vendor ->
                            vendor.displayName.lowercase().startsWith(userFilter.toString().lowercase())
                        }
                        binding.recyclerMain.adapter = VendorAdapter(vendorsFiltered, this@VendorFragment)
                    }
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("liveData", "${result.exception}")
                }
            }
        })



    }

    override fun onVendorClick(vendor: Vendor) {
        val action = VendorFragmentDirections.actionVendorFragmentToVendorDetailFragment(
            vendor.heroImage.url,
            vendor.displayName,
            vendor.description
        )
        findNavController().navigate(action)
    }

}