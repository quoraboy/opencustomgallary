package com.example.customgallary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class gallaryadaptorInsideFolder(context: Context, image: Array<String>?):RecyclerView.Adapter<gallaryadaptorInsideFolder.ViewHolder>()
{
    var context: Context
    var image: Array<String>?

    init {
        this.context = context
        this.image = image
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image_inside_folder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.gallary_inside_folder_item, parent, false)
        )
    }


    override fun getItemCount(): Int {
        if (image != null) {
            return image!!.size;
        }

        return 0; }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        image= image?.reversedArray()
        var imageConcat: String = image!!.get(position)
        Glide.with(context).load(imageConcat).into(holder.image)
        holder.itemView.setOnClickListener {
        }
    }

}