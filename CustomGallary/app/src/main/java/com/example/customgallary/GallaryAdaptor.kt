package com.example.customgallary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class GallaryAdaptor(
    context: Context,
    image: List<String>,
    hashMap: HashMap<String, String>,
    photolistner: PhotoListener
) :
    RecyclerView.Adapter<GallaryAdaptor.ViewHolder>() {
    var context: Context
    var image: List<String>
    var photolistner: PhotoListener
    lateinit var hashMap: HashMap<String, String>

    init {
        this.context = context
        this.image = image
        this.photolistner = photolistner
        this.hashMap = hashMap
    }

    interface PhotoListener {
        fun onPhotoClick(path: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foldername: TextView = itemView.findViewById(R.id.image)
        var image: ImageView = itemView.findViewById(R.id.image_beside_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.gallary_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var imageConcat: String = hashMap.get(image.get(position)).toString()
        var imagearray = imageConcat.split(",").toTypedArray()
        Glide.with(context).load(imagearray[imagearray.size - 1]).into(holder.image)
        holder.foldername.text = image.get(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, insidefolderrecycler::class.java)
            intent.putExtra("key",imagearray)
            context.startActivity(intent)
            photolistner.onPhotoClick(imagearray.size.toString())
        }
    }

    override fun getItemCount(): Int {
        if (image != null) {
            return image.size;
        }

        return 0; }
}