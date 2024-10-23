package com.example.onlineshoppet.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshoppet.Adapter.BestSellerAdapter
import com.example.onlineshoppet.Adapter.CategoryAdapter
import com.example.onlineshoppet.Adapter.SliderAdapter
import com.example.onlineshoppet.Model.SliderModel
import com.example.onlineshoppet.R
import com.example.onlineshoppet.ViewModel.MainViewModel
import com.example.onlineshoppet.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanners()
        initCategory()
        initBestSeller()
        bottomNavigation()

    }

    private fun bottomNavigation() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
    }

    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility=View.VISIBLE
        viewModel.bestSeller.observe(this, Observer {
            binding.viewBestSeller.layoutManager=GridLayoutManager(this,2)
            binding.viewBestSeller.adapter=BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility=View.GONE
        })
        viewModel.loadBestSeller()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()

    }

    private fun initBanners() {
        binding.progressBarBanner.visibility=View.VISIBLE
        viewModel.banner.observe(this, Observer {
            banners(it)
            binding.progressBarBanner.visibility=View.GONE
        })
        viewModel.loadBanners()


    }
    private fun banners(image: List<SliderModel>){
        binding.viewPagerSlide.adapter=SliderAdapter(image,binding.viewPagerSlide)
        binding.viewPagerSlide.clipToPadding=false
        binding.viewPagerSlide.clipChildren=false
        binding.viewPagerSlide.offscreenPageLimit=3
        binding.viewPagerSlide.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlide.setPageTransformer(compositePageTransformer)
        if(image.size>1){
            binding.dotIndicator.visibility= View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlide)
        }

    }
}