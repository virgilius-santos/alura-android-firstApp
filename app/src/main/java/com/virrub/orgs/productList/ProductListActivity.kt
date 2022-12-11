package com.virrub.orgs.productList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.virrub.orgs.ProductsDAO
import com.virrub.orgs.R
import com.virrub.orgs.productForm.ProductFormActivity

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {

    private val productsDAO = ProductsDAO()
    private val productListRecyclerView: RecyclerView by lazy { findViewById(R.id.productList) }
    private val addFAB: FloatingActionButton by lazy { findViewById(R.id.list_bt_add) }
    private val productListAdapter = ProductListAdapter(this, productsDAO.findProducts())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListRecyclerView.adapter = productListAdapter
        addFAB.setOnClickListener {
            goToProductForm()
        }
    }

    private fun goToProductForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        productListAdapter.update(productsDAO.findProducts())
    }
}