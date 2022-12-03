package com.virrub.orgs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ProductListAdapter(
    private val context: Context,
    private val productList: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(view: View) : ViewHolder(view) {

        private val nameTextView: TextView by lazy { itemView.findViewById(R.id.name) }
        private val descriptionTextView: TextView by lazy { itemView.findViewById(R.id.description) }
        private val valueTextView: TextView by lazy { itemView.findViewById(R.id.value) }

        fun vincula(product: Product) {
            nameTextView.text = product.name
            descriptionTextView.text = product.description
            valueTextView.text = product.value.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProductListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.vincula(productList[position])
    }

    override fun getItemCount(): Int = productList.size

}
