package com.dimonkiv.cardscanner.ui.modules.main.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.File

import android.os.Environment
import android.support.v4.content.FileProvider
import com.dimonkiv.cardscanner.BuildConfig
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.ui.modules.main.cardrecognition.CardRecognitionFragment
import com.dimonkiv.cardscanner.utill.FragmentById
import java.io.IOException






class CameraFragment : Fragment(), CameraContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: CameraPresenter
    private lateinit var view: CameraView
    private var currentPhotoPath = ""

    private val CAMERA_STORAGE_REQUEST_CODE = 100
    private val ONLY_CAMERA_REQUSET_CODE = 101
    private val ONLY_STORAGE_REQUEST_CODE = 102
    private val WRITE_CONTACT_PERMISSION = 103
    private val CAMERA_ACTION_PICK_REQUEST_CODE = 1000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_camera, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CameraPresenter(this)
    }

    private fun initView() {
        view = CameraView(getMainActivity(), context!!, presenter, root)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

    override fun checkPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraPermission = activity?.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            val storagePermission = activity?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

            if (!cameraPermission && !storagePermission) {
                callPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), CAMERA_STORAGE_REQUEST_CODE)
                return false
            }

            if (!cameraPermission && storagePermission) {
                callPermissions(arrayOf(Manifest.permission.CAMERA), ONLY_CAMERA_REQUSET_CODE)
                return false
            }

            if (cameraPermission && !storagePermission) {
                callPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), ONLY_STORAGE_REQUEST_CODE)
                return false
            }

            return true
        }

        return false
    }

    private fun callPermissions(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity!!, permissions, requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_STORAGE_REQUEST_CODE -> presenter.checkCameraAndStoragePermissionResult(grantResults)

            ONLY_CAMERA_REQUSET_CODE -> presenter.checkOnlyCameraPermissionResult(grantResults)

            ONLY_STORAGE_REQUEST_CODE -> presenter.checkOnlyStoragePermissionResult(grantResults)
        }

    }

    override fun openCamera() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File
        try {
            file = getImageFile() // 1
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }

        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        // 2
            FileProvider.getUriForFile(context!!, BuildConfig.APPLICATION_ID + ".provider", file)
        else
            Uri.fromFile(file) // 3
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri) // 4
        startActivityForResult(pictureIntent, CAMERA_ACTION_PICK_REQUEST_CODE)
    }



    @Throws(IOException::class)
    private fun getImageFile(): File {
        val imageFileName = "JPEG_" + System.currentTimeMillis() + "_"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            ), "Camera"
        )
        println(storageDir.absolutePath)
        if (storageDir.exists())
            println("File exists")
        else
            println("File not exists")
        val file = File.createTempFile(
            imageFileName, ".jpg", storageDir
        )
        currentPhotoPath = "file:" + file.absolutePath
        return file
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_ACTION_PICK_REQUEST_CODE) {
            presenter.onReceivePhotoFromCamera(currentPhotoPath)
        }
    }

    override fun showCardRecognizedFragment(photoPath: String) {
        getMainActivity().changeFragment(FragmentData(FragmentById.CARD_RECOGNITION_FRAGMENT,
                CardRecognitionFragment.getBundle(photoPath)))
    }
}