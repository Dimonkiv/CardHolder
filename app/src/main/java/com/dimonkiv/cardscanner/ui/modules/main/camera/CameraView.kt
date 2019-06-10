package com.dimonkiv.cardscanner.ui.modules.main.camera

import android.content.Context
import android.net.Uri
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity


class CameraView(private val activity: MainActivity,
                 private val context: Context,
                 private val presenter: CameraPresenter,
                 private val view: View) : CameraContract.View {

    private lateinit var toolbar: Toolbar
    private lateinit var photoPreviewIV: ImageView
    private lateinit var noPhotoContainerLL: LinearLayout
    private lateinit var takePhotoFAB: FloatingActionButton
    private lateinit var replayPhotoIV: ImageView

    init {
        initUI()
        initToolbar()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        photoPreviewIV = view.findViewById(R.id.image_preview_iv)
        noPhotoContainerLL = view.findViewById(R.id.no_photo_message_ll)
        takePhotoFAB = view.findViewById(R.id.take_photo_fab)
        replayPhotoIV = view.findViewById(R.id.replay_iv)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = "Камера"
    }

    private fun setListeners() {
        takePhotoFAB.setOnClickListener {
            presenter.onTakePhotoButtonClick()
        }

        replayPhotoIV.setOnClickListener {
            presenter.onReplayPhotoButtonClick()
        }
    }

    override fun setCreatePhotoMode() {
        photoPreviewIV.visibility = View.VISIBLE
        noPhotoContainerLL.visibility = View.GONE
        replayPhotoIV.visibility = View.VISIBLE
        takePhotoFAB.setImageResource(R.drawable.icon_done_white)
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showPhoto(imageUri: Uri) {
        Glide.with(context)
            .load(imageUri)
            .into(photoPreviewIV)
    }
}