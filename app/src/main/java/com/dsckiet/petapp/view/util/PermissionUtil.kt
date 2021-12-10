package com.dsckiet.petapp.view.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class PermissionUtil{
    fun forStoragePermission(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}