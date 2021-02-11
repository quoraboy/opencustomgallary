package com.example.customgallary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.customgallary.databinding.ActivityInsidefolderrecyclerBinding

class insidefolderrecycler : AppCompatActivity() {
    lateinit var binding: ActivityInsidefolderrecyclerBinding
    lateinit var gallaryAdaptorInside:gallaryadaptorInsideFolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsidefolderrecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list=intent.getStringArrayExtra("key")
        loadImages(list)
    }

    private fun loadImages(list: Array<String>?) {
        binding.RecyclerGallaryImageInsideFolder.setHasFixedSize(true)
        binding.RecyclerGallaryImageInsideFolder.layoutManager = GridLayoutManager(this, 3)
        gallaryAdaptorInside = gallaryadaptorInsideFolder(this,list)
        binding.RecyclerGallaryImageInsideFolder.adapter = gallaryAdaptorInside
        binding.gallaryNumberInsideFolder.text = "Photo(${list?.size})"
    }
}