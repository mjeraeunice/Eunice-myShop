package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Product
import model.ProductResponse
import repository.ProductRepository

class ProductsViewModel:ViewModel(){
    var productrepo = ProductRepository()
    var productsLiveData = MutableLiveData<List<Product>>()
    var errorLiveData = MutableLiveData<String>()

    fun fetchProducts() {
        viewModelScope.launch {
            val response = productrepo.getProducts()

            if (response.isSuccessful) {
                productsLiveData.postValue(response.body()?.products)
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }


        }
    }
}