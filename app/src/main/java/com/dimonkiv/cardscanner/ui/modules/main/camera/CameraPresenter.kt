package com.dimonkiv.cardscanner.ui.modules.main.camera

import android.content.pm.PackageManager
import android.net.Uri


class CameraPresenter(private val fragment: CameraFragment) :
    CameraContract.Presenter {
    private lateinit var view: CameraView
    private var currentPhotoPaths = ""

    init {

    }

    override fun setView(view: CameraView) {
        this.view = view
    }

    override fun onTakePhotoButtonClick() {
        if (currentPhotoPaths.isNotEmpty()) {
            fragment.showCardRecognizedFragment(currentPhotoPaths)
            return
        }

        if (fragment.checkPermissions()) {
            view.setCreatePhotoMode()
            fragment.openCamera()
            return
        }

        view.showMessage("Додатку потрібен дозвіл на використання камери!")
    }

    override fun onReceivePhotoFromCamera(currentPhotoPaths: String) {
        val uri = Uri.parse(currentPhotoPaths)
        view.showPhoto(uri)
        this.currentPhotoPaths = currentPhotoPaths
    }

    override fun onReplayPhotoButtonClick() {
        fragment.openCamera()
    }

    override fun checkCameraAndStoragePermissionResult(grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            fragment.openCamera()
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED) {
            view.showMessage("Додатку потрібен дозвіл на зберігання фотографії!")
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            view.showMessage("Додатку потрібен дозвіл на використання камери!")
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_DENIED) {
            view.showMessage("Додатку потрібен дозвіл на використання камери та сховища!")
            return
        }
    }

    override fun checkOnlyCameraPermissionResult(grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fragment.openCamera()
            return
        }

        view.showMessage("Додатку потрібен дозвіл на використання камери!")
    }

    override fun checkOnlyStoragePermissionResult(grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fragment.openCamera()
            return
        }

        view.showMessage("Додатку потрібен дозвіл на зберігання фотографії!")
    }

}