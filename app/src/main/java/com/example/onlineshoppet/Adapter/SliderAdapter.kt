package com.example.onlineshoppet.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshoppet.Model.SliderModel
import com.example.onlineshoppet.databinding.SliderImageContainerBinding

class SliderAdapter(
    private var sliderItems : List<SliderModel>,
    private val viewPager2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){

    private lateinit var context: Context
    private val runnable = Runnable {
        sliderItems=sliderItems
        notifyDataSetChanged()
    }
    class SliderViewHolder(val binding :SliderImageContainerBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setImage(sliderItems:SliderModel,context : Context){
            val requestOptions=RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(sliderItems.url)
                .apply(requestOptions)
                .into(binding.imageSlide)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context=parent.context
        val binding=SliderImageContainerBinding.inflate(LayoutInflater.from(context),parent,false)
        return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position],context)

        if(position==sliderItems.size-1){
            viewPager2.post(runnable)
        }
    }
}