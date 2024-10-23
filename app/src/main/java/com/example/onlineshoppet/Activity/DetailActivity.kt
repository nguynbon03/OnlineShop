package com.example.onlineshoppet.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshoppet.Adapter.PicListAdapter
import com.example.onlineshoppet.Adapter.SizeListAdapter
import com.example.onlineshoppet.Model.ItemsModel
import com.example.onlineshoppet.R
import com.example.onlineshoppet.databinding.ActivityDetailBinding
import com.example.onlineshoppet.databinding.ViewholderPicListBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart= ManagmentCart(this)
        getBundle()
        initList()
    }

    private fun initList() {
        val sizeList= ArrayList<String>()
        for(size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter=SizeListAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val colorList=ArrayList<String>()
        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)
        binding.picList.adapter=PicListAdapter(colorList,binding.picMain)
        binding.picList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.SellerNameTxt.text=item.sellerName

        binding.addToCartBtn.setOnClickListener {
            item.numberInCart=numberOrder
            managmentCart.insertItems(item)
        }
        binding.backBtn.setOnClickListener{
            finish()
        }
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }
        Glide.with(this)
            .load(item.sellerPic)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.picSeller)
        binding.msgToSellerBtn.setOnClickListener{
            val sendItent=Intent (Intent.ACTION_VIEW)
            sendItent.setData(Uri.parse("sms:"+item.sellerTell))
            sendItent.putExtra("sms_body","type your message")
            startActivity(sendItent)
        }
        binding.callToSellerBtn.setOnClickListener{
            val phone=item.sellerTell.toString()
            val intent=Intent (Intent.ACTION_DIAL,Uri.fromParts("tel",phone,null))
            startActivity(intent)
        }
    }
}