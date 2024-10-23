package com.example.onlineshoppet.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshoppet.R
import com.example.onlineshoppet.databinding.ViewholderPicListBinding

class PicListAdapter(val items:MutableList<String>,var picMain: ImageView): RecyclerView.Adapter<PicListAdapter.Viewholder>()  {

    private var selectedPosition =-1
    private var lastSelectedPosition =-1
    private lateinit var context: Context
    inner class Viewholder(val binding:ViewholderPicListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(items:String,context: Context){
            val requestOptions= RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(items)
                .apply(requestOptions)
                .into(binding.picList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderPicListBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PicListAdapter.Viewholder, position: Int) {
        holder.bind(items[position],context)
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Glide.with(context)
                .load(items[position])
                .into(picMain)
        }
        if(selectedPosition==position){
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        }else{
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}