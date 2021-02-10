package com.example.customgallary

import android.content.Context
import android.database.Cursor
import android.graphics.Camera
import android.net.Uri
import android.provider.MediaStore

class ImageGallary {
    lateinit var hashMap: HashMap<String, String>

    fun getter(): HashMap<String, String> {
        return hashMap
    }

    fun listofImage(context: Context): ArrayList<String> {
        var listofallimage = ArrayList<String>()
        var listoffolder = ArrayList<String>()
        lateinit var uri: Uri
        var cursor: Cursor? = null
        var coloum_index_data: Int
        var coloum_index_folder_name: Int
        lateinit var AbsolutePathofImage: String
        lateinit var AbsolutePathofImageFolder: String
      hashMap=HashMap()

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI //FROM table_name
        var projection =
                arrayOf("COUNT(*) as count", "GROUP_CONCAT (_data,',') as " + MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)//projection will decide which columns to return
        //"GROUP_CONCAT( MediaStore.MediaColumns.DATA ) as "+ MediaStore.MediaColumns.DATA
        //MediaStore.MediaColumns.DATA---> this columns contains path of images
        //MediaStore.Images.Media.BUCKET_DISPLAY_NAME ---> this columns contains folder name containing images
        var orderBy: String = MediaStore.Video.Media.DATE_TAKEN
        cursor = context.contentResolver.query(uri, projection, "1) GROUP BY (" + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, null, orderBy + " DESC")

//        contentResolver adds parentheses when compiling resulting query for sqlLite, so if we make selection like
//
//        "GROUP BY " + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME
//
//        it will be compiled as "WHERE (GROUP BY bucket_display_name)" and will cause SQLiteException at runtime. Otherwise if we make selection like
//
//        "1) GROUP BY (" + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME
//
//        it will be compiled as "WHERE (1) GROUP BY (bucket_display_name)", which is correct

        coloum_index_data = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)!!//try catch

        //getfoldername
        coloum_index_folder_name = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        while (cursor?.moveToNext()) {
            AbsolutePathofImageFolder = cursor?.getString(coloum_index_folder_name)
            listoffolder.add(AbsolutePathofImageFolder)

            AbsolutePathofImage = cursor?.getString(coloum_index_data)
            listofallimage.add(AbsolutePathofImage)

            hashMap.put(AbsolutePathofImageFolder, AbsolutePathofImage)


        }

        return listoffolder
    }
}