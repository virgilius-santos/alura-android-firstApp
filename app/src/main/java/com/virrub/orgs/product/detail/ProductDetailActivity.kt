package com.virrub.orgs.product.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.virrub.orgs.R
import com.virrub.orgs.databinding.ActivityProductDetailBinding
import com.virrub.orgs.extensions.currenceValue
import com.virrub.orgs.extensions.tryLoad
import com.virrub.orgs.product.model.Product
import com.virrub.orgs.product.model.ProductTransition
import com.virrub.orgs.product.model.toProduct

class ProductDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        val binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return@lazy binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val product: Product? = intent?.getParcelableExtra("product", ProductTransition::class.java)?.toProduct()
        product
            ?.apply {
                binding.formImgProduct.tryLoad(url)
                binding.productDetailValue.setText(value.currenceValue())
                binding.productDetailName.setText(name)
                binding.productDetailDescription.setText(description)
            } ?: run {
            Log.i(TAG, "finish activity ")
            finish()
        }
    }
}