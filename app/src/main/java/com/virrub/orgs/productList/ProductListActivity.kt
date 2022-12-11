package com.virrub.orgs.productList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.virrub.orgs.R
import com.virrub.orgs.productForm.ProductFormActivity
import java.math.BigDecimal

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {

    private val productListRecyclerView: RecyclerView by lazy { findViewById(R.id.productList) }
    private val addFAB: FloatingActionButton by lazy { findViewById(R.id.list_bt_add) }

    private val products = listOf(
        Product(name = "name 1", description = "description", value = BigDecimal("19.99")),
        Product(name = "name 2", description = "description", value = BigDecimal("19.99")),
        Product(name = "name 3", description = "description", value = BigDecimal("19.99"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productListRecyclerView.adapter = ProductListAdapter(this, products)

        addFAB.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }
}