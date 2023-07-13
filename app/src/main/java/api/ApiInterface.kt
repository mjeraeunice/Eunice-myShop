package api

import model.Product
import model.ProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/product")
    suspend fun getProducts(): Response<ProductResponse>

    @GET("/product/{id}")
    suspend fun getProductsById(@Path("id") productId:Int):Response<Product>

}