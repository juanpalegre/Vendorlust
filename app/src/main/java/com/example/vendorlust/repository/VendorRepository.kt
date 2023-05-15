package com.example.vendorlust.repository

import com.example.vendorlust.data.model.VendorList

interface VendorRepository {

    suspend fun getAllVendors(): VendorList

}