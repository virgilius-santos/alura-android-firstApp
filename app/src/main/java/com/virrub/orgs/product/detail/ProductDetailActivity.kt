package com.virrub.orgs.product.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.virrub.orgs.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        val binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return@lazy binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}