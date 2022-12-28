package com.virrub.orgs.product.form

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.virrub.orgs.R
import com.virrub.orgs.databinding.ActivityProductFormBinding
import com.virrub.orgs.extensions.tryLoad
import com.virrub.orgs.imageForm.ImageFormDialog
import com.virrub.orgs.product.model.Product
import com.virrub.orgs.product.model.ProductsDAO
import java.math.BigDecimal
import java.time.Instant.*
import java.time.ZoneId
import java.time.ZoneOffset

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        val binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return@lazy binding
    }

    private val productsDAO = ProductsDAO()
    private val nameEditText: EditText by lazy { binding.edtName }
    private val descriptionEditText: EditText by lazy { binding.edtDescription }
    private val valueEditText: EditText by lazy { binding.edtValue }
    private val saveButton: Button by lazy { binding.formBtnSave }
    private val imageView: ImageView by lazy { binding.formImgProduct }
    private val dateTextView: TextView by lazy { binding.formLayoutEdtDate }

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

    val dateText: String
        get() {
            return dateTextView.text.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        dateTextView.setOnClickListener {
            openDatePicker()
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
            imageURL,
            dateText
        )
        Log.i("ProductFormActivity", "onEvent: $product")
        productsDAO.add(product)
    }

    private fun openDatePicker() {
        val dateSelector = MaterialDatePicker
            .Builder.datePicker().build()
        dateSelector.show(supportFragmentManager, "MATERIAL_DATE_PICKER")
        dateSelector
            .addOnPositiveButtonClickListener { dataEmMilisegundos ->
                val data = ofEpochMilli(dataEmMilisegundos)
                    .atZone(ZoneId.of("America/Sao_Paulo"))
                    .withZoneSameInstant(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
                    .toLocalDate()
                Log.i("MaterialDatePicker", "data com LocalDate: $data")
                dateTextView.setText(data.toString())
            }
    }
}