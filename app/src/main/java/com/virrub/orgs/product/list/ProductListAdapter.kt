package com.virrub.orgs.product.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.virrub.orgs.databinding.ProductItemBinding
import com.virrub.orgs.extensions.tryLoad
import com.virrub.orgs.product.model.Product
import java.text.NumberFormat
import java.util.*

class ProductListAdapter(
    private val context: Context,
    productList: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(binding: ProductItemBinding) : ViewHolder(binding.root) {

        private val nameTextView: TextView by lazy { binding.name }
        private val descriptionTextView: TextView by lazy { binding.description }
        private val valueTextView: TextView by lazy { binding.value }
        private val imageView: ImageView by lazy { binding.imgProduct }

        fun vincula(product: Product) {
            nameTextView.text = product.name
            descriptionTextView.text = product.description
            valueTextView.text = NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(product.value)
            imageView.visibility = if (product.url == null) {
                View.GONE
            } else {
                View.VISIBLE
            }
            imageView.tryLoad(product.url)
        }
    }

    private val productList = productList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.vincula(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun update(products: List<Product>) {
        this.productList.clear()
        this.productList.addAll(products)
        notifyDataSetChanged()
    }

}
