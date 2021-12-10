package com.dsckiet.petapp.view.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import java.io.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Utility {

    fun checkPassword(password: String): Boolean {
        val pattern: Pattern
        val pPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(pPattern)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }

    fun loadImageFromStorage(path: String): Bitmap? {
        return try {
            val f = File(path, "image.jpg")
            BitmapFactory.decodeStream(FileInputStream(f))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    fun uriToBitmap(context: Context, selectedFileUri: Uri): Bitmap? {
        return try {
            val parcelFileDescriptor: ParcelFileDescriptor? =
                context.contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor? = parcelFileDescriptor?.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor?.close()
            image
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun saveToInternalStorage(bitmapImage: Bitmap, id: Int, activity: Activity): String? {
        val cw = ContextWrapper(activity.applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val myPath = File(directory, "image$id.jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(myPath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return directory.absolutePath
    }
}