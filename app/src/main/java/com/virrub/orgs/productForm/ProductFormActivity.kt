package com.virrub.orgs.productForm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.virrub.orgs.R

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val nameEditText: EditText by lazy { findViewById(R.id.edt_name) }
    private val descriptionEditText: EditText by lazy { findViewById(R.id.edt_description) }
    private val valueEditText: EditText by lazy { findViewById(R.id.edt_value) }
    private val saveButton: Button by lazy { findViewById(R.id.form_btn_save) }

    val nameText: String
        get() { return nameEditText.text.toString() }

    val descriptionText: String
        get() { return descriptionEditText.text.toString() }

    val valueText: String
        get() { return valueEditText.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        saveButton.setOnClickListener {
            Log.i("ProductFormActivity", "onEvent: $nameText")
        }
    }
}