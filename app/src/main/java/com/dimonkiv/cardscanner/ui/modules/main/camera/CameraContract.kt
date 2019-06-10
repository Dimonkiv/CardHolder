package com.dimonkiv.cardscanner.ui.modules.main.camera


import android.net.Uri


interface CameraContract {

    interface View {
        fun setCreatePhotoMode()

        fun showMessage(message: String)

        fun showPhoto(imageUri: Uri)
    }

    interface Presenter {
        fun setView(view: CameraView)

        fun onTakePhotoButtonClick()

        fun onReplayPhotoButtonClick()

        fun checkCameraAndStoragePermissionResult(grantResults: IntArray)

        fun checkOnlyCameraPermissionResult(grantResults: IntArray)

        fun checkOnlyStoragePermissionResult(grantResults: IntArray)

        fun onReceivePhotoFromCamera(currentPhotoPaths: String)
    }

    interface Fragment {
        fun checkPermissions(): Boolean

        fun openCamera()

        fun showCardRecognizedFragment(photoPath: String)
    }
}