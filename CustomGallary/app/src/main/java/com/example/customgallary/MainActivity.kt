package com.example.customgallary

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.customgallary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var gallaryAdaptor: GallaryAdaptor
    lateinit var images: List<String>
    val my_read_permission_code: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),my_read_permission_code)
        }
        else
        {
            loadImages()
        }
    }

    private fun loadImages() {
        binding.RecyclerGallaryImage.setHasFixedSize(true)
        binding.RecyclerGallaryImage.layoutManager = GridLayoutManager(this, 3)
        var imageGallary = ImageGallary()
        images = imageGallary.listofImage(this)
        gallaryAdaptor = GallaryAdaptor(this, images, object : GallaryAdaptor.PhotoListener {
            override fun onPhotoClick(path: String) {
                Toast.makeText(this@MainActivity, path, Toast.LENGTH_LONG).show()
            }

        })
        binding.RecyclerGallaryImage.adapter = gallaryAdaptor
        binding.gallaryNumber.text = "Photo(${images.size})"
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults!!)
        if (requestCode == my_read_permission_code) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "granted permission", Toast.LENGTH_LONG).show()
                loadImages()
            } else {
                Toast.makeText(this, "NOT granted permission", Toast.LENGTH_LONG).show()
            }
        }
    }
}
