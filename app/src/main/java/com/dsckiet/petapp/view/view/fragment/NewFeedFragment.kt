package com.dsckiet.petapp.view.view.fragment

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dsckiet.petapp.databinding.FragmentNewFeedBinding
import com.dsckiet.petapp.view.util.LocalKeyStorage
import com.dsckiet.petapp.view.util.PermissionUtil
import com.dsckiet.petapp.view.util.Utility
import com.dsckiet.petapp.view.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class NewFeedFragment : Fragment() {

    private lateinit var binding: FragmentNewFeedBinding
    private lateinit var viewModel: ViewModel
    private lateinit var utility: Utility
    val pickImage = 1
    var path: String? = null
    private lateinit var file: File


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewFeedBinding.inflate(layoutInflater)

        utility = Utility()

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.feedToolbar.toolbarTitle.text = "New Feed"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.feedToGallery.setOnClickListener {
            if (requestStoragePermission()) {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickImage)
            }
        }

        binding.feedPost.setOnClickListener {
            when {
                binding.feedEventHeading.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        "Please fill the Event heading",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.feedCaption.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        "Please fill the Caption for the post",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    //viewModel.postFeedImage()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == pickImage) {
            val pickedImage: Uri? = data?.data
            val imagePath = pickedImage?.path
            pickedImage?.let {
                val bitmap = utility.uriToBitmap(requireContext(), pickedImage)
                bitmap?.let {
                    Log.d("ImageUpload", "onActivityResult: Bitmap = $it")
                    path = utility.saveToInternalStorage(bitmap, 0, requireActivity())
                    Glide.with(requireContext())
                        .load(bitmap)
                        .into(binding.feedImage)
                    file = File(imagePath)
                    Log.d("ImageUpload", "onActivityResult: Path = $path")
                    Log.d("ImageUpload", "onActivityResult: forFile = $pickedImage")
                    Log.d("ImageUpload", "onActivityResult: $file")
                }
            }
            TODO("Upload image to server")
            if (imagePath != null) {
                uploadImage(File(imagePath), imagePath,pickedImage)
            }
            if (path != null) {
                binding.feedToGallery.visibility = View.GONE
                binding.feedPost.visibility = View.VISIBLE
            }
            Log.d("ImageUpload", "onActivityResult: Path = $path")
        }
    }

    private fun requestStoragePermission(): Boolean {
        if (!PermissionUtil().forStoragePermission(requireContext())) {
            val snackBar: Snackbar =
                Snackbar.make(
                    binding.feedImage,
                    "Storage permission required",
                    Snackbar.LENGTH_LONG
                )
//            snackBar.setBackgroundTint(resources.getColor(Color.GRAY))
//            snackBar.setActionTextColor()
            snackBar.setAction("Enable") {
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri =
                    Uri.fromParts("package", activity?.packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            snackBar.show()
        }
        return PermissionUtil().forStoragePermission(requireContext())
    }


    private fun uploadImage(file: File, imagePath: String,pickedImage:Uri) {
       path = uploadImage(pickedImage)
//        val requestBody: RequestBody = RequestBody.create(MediaType.get("image/*"), file)
//        val parts: MultipartBody.Part =
//            MultipartBody.Part.createFormData("newImage", file.name, requestBody)
////        val form = RequestBody.create(MultipartBody.FORM, "2")
////        val notes = RequestBody.create(MultipartBody.FORM, "anyType")
//
  val cookie = LocalKeyStorage(requireContext()).getValue(LocalKeyStorage.COOKIE).toString()
//
//        val fileName: String =
//            imagePath.subSequence(imagePath.lastIndexOf('/'), imagePath.length).toString()
//        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
//            .addFormDataPart(
//                "file", fileName,
//                RequestBody.create(
//                    MediaType.parse("application/octet-stream"),
//                    file
//                )
//            )
//            .build()

        val file = File(imagePath)

        val requestFile = RequestBody.create(
            MediaType.parse(context?.contentResolver?.getType(pickedImage)),
            file
        )

        // MultipartBody.Part is used to send also the actual file name

        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("picture", file.name, requestFile)

        // add another part within the multipart request

        // add another part within the multipart request
        val descriptionString = "hello, this is description speaking"
        val description = RequestBody.create(
            MultipartBody.FORM, descriptionString
        )



        viewModel.postFeedImage(description, body, cookie)
    }


    private fun uploadImage(fileUri: Uri): String? {
        val filePathColumn =
            arrayOf(MediaStore.Images.Media.DATA)

        val cursor: Cursor? = activity?.contentResolver?.query(
            fileUri,
            filePathColumn, null, null, null
        )
        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            val picturePath: String? = cursor.getString(columnIndex)
            cursor.close()
            return picturePath
           // Log.d("path", picturePath)
        }
        return null

    }

    private fun mUploadImage(path: String) {
        val file: File = File(path)
//        val retrofit: Retrofit = NetworkClient.getRetrofit()
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val parts = MultipartBody.Part.createFormData("newimage", file.name, requestBody)
        val someData = RequestBody.create(MediaType.parse("text/plain"), "This is a new Image")
        val cookie = LocalKeyStorage(requireContext()).getValue(LocalKeyStorage.COOKIE).toString()

        //viewModel.postFeedImage(parts, cookie)
    }
}