package com.virrub.orgs.productForm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.virrub.orgs.R
import com.virrub.orgs.databinding.ActivityProductFormBinding
import com.virrub.orgs.extensions.tryLoad
import com.virrub.orgs.imageForm.ImageFormDialog
import com.virrub.orgs.productModel.Product
import com.virrub.orgs.productModel.ProductsDAO
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
    private val imageView: ImageView by lazy { binding.formImgProduct }

    private val imageDialog: ImageFormDialog
        get() { return ImageFormDialog(this, imageURL) }

    val nameText: String
        get() {
            return nameEditText.text.toString()
        }

    val descriptionText: String
        get() {
            return descriptionEditText.text.toString()
        }

    val valueText: String
        get() {
            return valueEditText.text.toString()
        }

    var imageURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = getString(R.string.product_form_title)
        saveButton.setOnClickListener {
            createProduct()
            finish()
        }

        imageView.setOnClickListener {
            imageDialog.showImageSelector(onSuccess = { imageURL ->
                loadImageFromURLToForm(imageURL)
            })
        }
    }

    private fun loadImageFromURLToForm(imageURL: String) {
        this.imageURL = imageURL
        binding.formImgProduct.tryLoad(imageURL)
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
            value,
            imageURL
        )
        Log.i("ProductFormActivity", "onEvent: $product")
        productsDAO.add(product)
    }
}