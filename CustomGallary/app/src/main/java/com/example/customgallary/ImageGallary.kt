package com.example.customgallary

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import kotlin.properties.Delegates

class ImageGallary {
    fun listofImage(context: Context): ArrayList<String> {
        var listofallimage = ArrayList<String>()
        lateinit var uri: Uri
        var cursor: Cursor? =null
        var coloum_index_data: Int
        var coloum_index_folder_name : Int
        lateinit var AbsolutePathaOfImage: String
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        var orderBy: String = MediaStore.Video.Media.DATE_TAKEN
        cursor = context.contentResolver.query(uri, projection, null, null, orderBy + " DESC")
        coloum_index_data = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)!!

        //getfoldername
        coloum_index_folder_name = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor?.moveToNext()) {
            AbsolutePathaOfImage = cursor?.getString(coloum_index_data)
            listofallimage.add(AbsolutePathaOfImage)
        }

        return listofallimage
    }
}