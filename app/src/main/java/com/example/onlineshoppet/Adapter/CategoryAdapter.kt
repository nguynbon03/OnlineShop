package com.example.onlineshoppet.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshoppet.Model.CategoryModel
import com.example.onlineshoppet.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items: MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context: Context
    inner class Viewholder(val binding:ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel,context: Context){
            val requestOptions=RequestOptions().transform(CenterInside())
            binding.titleCat.text=item.title
            Glide.with(context)
                .load(item.picUrl)
                .apply(requestOptions)
                .into(binding.picCat)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        holder.bind(items[position],context)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}