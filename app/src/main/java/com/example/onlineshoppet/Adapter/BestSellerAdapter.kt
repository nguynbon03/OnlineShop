package com.example.onlineshoppet.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshoppet.Activity.DetailActivity
import com.example.onlineshoppet.Model.ItemsModel
import com.example.onlineshoppet.databinding.ViewholderBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<BestSellerAdapter.Viewholder>() {
    private var context: Context? = null

    class Viewholder(val binding: ViewholderBestSellerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsModel, context: Context?) {
            context?.let {
                val requestOptions = RequestOptions().transform(CenterCrop())
                binding.titleTxt.text = item.title
                binding.priceTxt.text = "$" + item.price.toString()
                binding.ratingTxt.text = item.rating.toString()
                Glide.with(it)
                    .load(item.picUrl[0])
                    .apply(requestOptions)
                    .into(binding.picBestSeller)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderBestSellerBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BestSellerAdapter.Viewholder, position: Int) {
        holder.bind(items[position], context)
        holder.binding.root.setOnClickListener{
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
