package com.virrub.orgs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val productListRecyclerView: RecyclerView by lazy { findViewById(R.id.productList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productListRecyclerView.adapter = ProductListAdapter(this, listOf(
            Product(name = "name 1", description =  "description", value = BigDecimal("19.99")),
            Product(name = "name 2", description =  "description", value = BigDecimal("19.99")),
            Product(name = "name 3", description =  "description", value = BigDecimal("19.99"))
        ))
    }
}