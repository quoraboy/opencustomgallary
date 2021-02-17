package com.example.customgallary

import android.content.Context
import android.database.Cursor
import android.graphics.Camera
import android.net.Uri
import android.provider.MediaStore

class ImageGallary {
    lateinit var hashMap: HashMap<String, String>
    lateinit var uri: Uri
    var appendingallimages: String? = null
    var listofallimage = ArrayList<String>()

    fun getter(): HashMap<String, String> {
        return hashMap
    }

//    fun getterListofImage(): ArrayList<String> {
//     return listofallimage
//    }

    fun listofImage(context: Context): ArrayList<String> {
        var listoffolder = HashSet<String>()
        var listofimageid = ArrayList<Int>()
        var imageid: Long

        var cursor: Cursor? = null
        var coloum_index_data: Int
        var coloum_index_folder_name: Int
        lateinit var AbsolutePathofImage: String
        lateinit var AbsolutePathofImageFolder: String
        hashMap = HashMap()

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI //FROM table_name
        var projection =
            arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
            )//projection will decide which columns to return
        //"GROUP_CONCAT( MediaStore.MediaColumns.DATA ) as "+ MediaStore.MediaColumns.DATA
        //MediaStore.MediaColumns.DATA---> this columns contains path of images
        //MediaStore.Images.Media.BUCKET_DISPLAY_NAME ---> this columns contains folder name containing images
        var orderBy: String = MediaStore.Video.Media.DATE_TAKEN
        cursor = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            orderBy + " DESC"
        )

//        contentResolver adds parentheses when compiling resulting query for sqlLite, so if we make selection like
//
//        "GROUP BY " + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME
//
//        it will be compiled as "WHERE (GROUP BY bucket_display_name)" and will cause SQLiteException at runtime. Otherwise if we make selection like
//
//        "1) GROUP BY (" + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME
//
//        it will be compiled as "WHERE (1) GROUP BY (bucket_display_name)", which is correct

        coloum_index_data = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media._ID)!!//try catch

        //getfoldername
        coloum_index_folder_name =
            cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        while (cursor?.moveToNext()) {

            AbsolutePathofImageFolder = cursor?.getString(coloum_index_folder_name)
            listoffolder.add(AbsolutePathofImageFolder)


            imageid = cursor?.getLong(coloum_index_data)

            val uriImage = Uri.withAppendedPath(uri, "" + imageid)
            listofimageid.add(imageid.toInt())
            //AbsolutePathofImage = cursor?.getString(coloum_index_data)

//            listofallimage.add(uriImage.toString())
            if (appendingallimages==null)
            {
                appendingallimages=uriImage.toString()
            }else
                appendingallimages = appendingallimages + "," + uriImage.toString()

            if (hashMap.containsKey(AbsolutePathofImageFolder)) {
                hashMap.put(
                    AbsolutePathofImageFolder,
                    hashMap.get(AbsolutePathofImageFolder) + "," + uriImage.toString()
                )
            } else {
                hashMap.put(AbsolutePathofImageFolder, uriImage.toString())
            }


            //  hashMap.put(uriImage.toString(), AbsolutePathofImageFolder)


        }
        hashMap.put("All Media", appendingallimages.toString())

       var arraylistoffolder=ArrayList<String>(listoffolder)
        arraylistoffolder.add(0,"All Media")
        arraylistoffolder.remove("Camera")
        arraylistoffolder.add(1,"Camera")
        return arraylistoffolder
    }
}