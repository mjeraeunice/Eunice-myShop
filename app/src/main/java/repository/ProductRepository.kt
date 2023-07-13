package repository

import api.ApiClient
import api.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.ProductResponse
import retrofit2.Response

class ProductRepository {
    val apiClient=ApiClient.buildClient(ApiInterface::class.java)

    suspend fun getProducts():Response<ProductResponse>{
        return withContext(Dispatchers.IO){
            apiClient.getProducts()
        }
    }
}