package com.example.vendorlust.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.vendorlust.core.Resource
import com.example.vendorlust.repository.VendorRepository
import kotlinx.coroutines.Dispatchers

class VendorViewModel(private val repo: VendorRepository): ViewModel() {

    fun fetchVendors() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getAllVendors()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class VendorViewModelFactory(private val repo: VendorRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(VendorRepository::class.java).newInstance(repo)
    }
}