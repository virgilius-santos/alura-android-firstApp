package com.virrub.orgs.productForm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.virrub.orgs.ProductsDAO
import com.virrub.orgs.R
import com.virrub.orgs.databinding.ActivityProductFormBinding
import com.virrub.orgs.productList.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private val productsDAO = ProductsDAO()
    private val nameEditText: EditText by lazy { binding.edtName }
    private val descriptionEditText: EditText by lazy { binding.edtDescription }
    private val valueEditText: EditText by lazy { binding.edtValue }
    private val saveButton: Button by lazy { binding.formBtnSave }

    val nameText: String
        get() { return nameEditText.text.toString() }

    val descriptionText: String
        get() { return descriptionEditText.text.toString() }

    val valueText: String
        get() { return valueEditText.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        saveButton.setOnClickListener {
            createProduct()
            finish()
        }
    }

    private fun createProduct() {
        val value = if (valueText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valueText)
        }
        val product = Product(
            nameText,
            descriptionText,
            value
        )
        Log.i("ProductFormActivity", "onEvent: $product")
        productsDAO.add(product)
    }
}