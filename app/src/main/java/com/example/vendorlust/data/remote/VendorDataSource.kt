package com.example.vendorlust.data.remote

import com.example.vendorlust.data.model.VendorList
import com.example.vendorlust.repository.WebService

class VendorDataSource(private val webService: WebService) {

    suspend fun getVendorList(): VendorList{
        return webService.getVendorList()
    }

}