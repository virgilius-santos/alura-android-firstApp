package com.virrub.orgs.productForm

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.virrub.orgs.ProductsDAO
import com.virrub.orgs.R
import com.virrub.orgs.databinding.ActivityProductFormBinding
import com.virrub.orgs.databinding.ImageFormBinding
import com.virrub.orgs.extensions.tryLoad
import com.virrub.orgs.productList.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    class ImageFormDialog(
        private var context: Context,
        private var url: String?
    ) {
        private val binding by lazy {
            ImageFormBinding.inflate(LayoutInflater.from(context)).apply {
                if (url == null) {
                    formImageInput.setText("https://asia.olympus-imaging.com/content/000107506.jpg")
                }
                url?.let {
                    formImageInput.setText(it)
                    imageFormImage.tryLoad(it)
                }
                imageFormButton.setOnClickListener {
                    loadImageFromURLToDialog()
                }
            }
        }

        private val imageURL: String
            get() {
                return binding.formImageInput.text.toString()
            }

        private fun loadImageFromURLToDialog() {
            binding.imageFormImage.tryLoad(imageURL)
        }

        fun showImageSelector(
            onSuccess: (String) -> Unit = {},
            onCancel: () -> Unit = {}
        ) {
            AlertDialog.Builder(context)
                .setView(binding.root)
                .setPositiveButton("confirmar") { _, _ ->
                    onSuccess(imageURL)
                }
                .setNegativeButton("cancelar") { _, _ ->
                    onCancel()
                }
                .show()
        }
    }

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