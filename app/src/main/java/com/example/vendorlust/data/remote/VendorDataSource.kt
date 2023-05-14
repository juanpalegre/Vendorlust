package com.example.vendorlust.data.remote

import com.example.vendorlust.data.model.VendorList

class VendorDataSource {

    fun getVendorList(): VendorList{
        return VendorList()
    }

}