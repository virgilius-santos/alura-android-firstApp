package com.virrub.orgs.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.virrub.orgs.R

class ProductListAdapter(
    private val context: Context,
    productList: List<Product>
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

    private val productList = productList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProductListViewHolder(view)
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
