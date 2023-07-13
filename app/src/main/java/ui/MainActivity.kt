package ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import model.ProductsAdapter
import com.eunice.my_shopeunice.databinding.ActivityMainBinding
import viewmodel.ProductsViewModel

class MainActivity : AppCompatActivity() {
   val productsViewModel: ProductsViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onResume() {
        super.onResume()
        productsViewModel.fetchProducts()
        productsViewModel.productsLiveData.observe(this, Observer { productsList ->
            var productAdapter = ProductsAdapter(productsList ?: emptyList())
            binding.rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvProducts.adapter = productAdapter

            Toast.makeText(
                baseContext,
                "fetched ${productsList?.size}products",
                Toast.LENGTH_LONG
            ).show()
        })
        productsViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG)
                .show()
        })


    }
}
