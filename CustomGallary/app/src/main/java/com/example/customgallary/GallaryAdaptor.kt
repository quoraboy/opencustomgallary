package com.example.customgallary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GallaryAdaptor(context: Context, image: List<String>, photolistner: PhotoListener) :
    RecyclerView.Adapter<GallaryAdaptor.ViewHolder>() {
    var context: Context
    var image: List<String>
    var photolistner: PhotoListener

    init {
        this.context = context
        this.image = image
        this.photolistner = photolistner
    }

    interface PhotoListener {
        fun onPhotoClick(path: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return  ViewHolder(
          LayoutInflater.from(context).inflate(R.layout.gallary_item,parent,false)
      )
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var image: String =image.get(position)
        Glide.with(context).load(image).into(holder.image)
        holder.itemView.setOnClickListener {
            photolistner.onPhotoClick(image)
        }
    }

    override fun getItemCount(): Int {
        if(image != null)
        {
            return image.size;
        }

        return 0;    }
}