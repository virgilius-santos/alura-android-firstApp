package com.virrub.orgs.product.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.virrub.orgs.databinding.ActivityProductListBinding
import com.virrub.orgs.product.detail.ProductDetailActivity
import com.virrub.orgs.product.form.ProductFormActivity
import com.virrub.orgs.product.model.ProductsDAO
import com.virrub.orgs.product.model.toTransition

class ProductListActivity : AppCompatActivity() {

    private val productsDAO = ProductsDAO()
    private val productListRecyclerView: RecyclerView by lazy { binding.productList }
    private val addFAB: FloatingActionButton by lazy { binding.listBtAdd }
    private val productListAdapter: ProductListAdapter by lazy {
        ProductListAdapter(this, productsDAO.findProducts()) {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", it.toTransition())
            startActivity(intent)
        }
    }

    private val binding by lazy {
        val binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return@lazy binding
    }

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