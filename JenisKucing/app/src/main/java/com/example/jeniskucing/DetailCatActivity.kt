package com.example.jeniskucing

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jeniskucing.databinding.ActivityDetailCatBinding


class DetailCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCatBinding
    companion object {
        const val DETAIL_CAT = "detail_cat"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataCat = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DETAIL_CAT, Cat::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DETAIL_CAT)
        }
        this.setTitle(getString(R.string.cat_detail) + " " + dataCat?.name)
        Log.d("test", dataCat.toString())
        Glide.with(this).load(dataCat?.photo.toString()).into(binding.detailImage)
        binding.tvCatDiscription.text = dataCat?.description.toString()
        binding.tvCatCaracter.text = dataCat?.caracater.toString()
        binding.actionShare.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, dataCat.toString())
            try {
                startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
                Log.d("test", "test")
            }
        }
    }
}