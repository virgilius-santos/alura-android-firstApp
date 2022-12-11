package com.virrub.orgs.productList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.virrub.orgs.R
import java.math.BigDecimal

class ProductListActivity : AppCompatActivity() {

    private val productListRecyclerView: RecyclerView by lazy { findViewById(R.id.productList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productListRecyclerView.adapter = ProductListAdapter(this, listOf(
            Product(name = "name 1", description =  "description", value = BigDecimal("19.99")),
            Product(name = "name 2", description =  "description", value = BigDecimal("19.99")),
            Product(name = "name 3", description =  "description", value = BigDecimal("19.99"))
        ))
    }
}