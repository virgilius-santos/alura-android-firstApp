package com.virrub.orgs.product.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.virrub.orgs.product.model.ProductsDAO
import com.virrub.orgs.databinding.ActivityProductListBinding
import com.virrub.orgs.product.form.ProductFormActivity

class ProductListActivity : AppCompatActivity() {

    private val productsDAO = ProductsDAO()
    private val productListRecyclerView: RecyclerView by lazy { binding.productList }
    private val addFAB: FloatingActionButton by lazy { binding.listBtAdd }
    private val productListAdapter = ProductListAdapter(this, productsDAO.findProducts())

    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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