package com.example.onlineshoppet.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshoppet.databinding.ActivityIntroBinding
import com.example.onlineshoppet.databinding.ActivityMainBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity,MainActivity::class.java))
        }
    }
}