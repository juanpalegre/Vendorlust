package com.example.vendorlust.repository

import com.example.vendorlust.data.model.VendorList
import com.example.vendorlust.data.remote.VendorDataSource

class VendorRepoImplement(private val dataSource: VendorDataSource): VendorRepository  {
    override suspend fun getAllVendors(): VendorList = dataSource.getVendorList()
}