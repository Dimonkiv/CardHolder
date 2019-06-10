package com.dimonkiv.cardscanner.utill


import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.dimonkiv.cardscanner.data.db.dao.RecognizedText
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer

class TextRecognitionHelper(private val photoPath: String,
                            private val context: Context,
                            private val callback: Callback) {
    private val recognizedTextList = ArrayList<RecognizedText>()
    private var imageFrame: Frame? = null
    interface Callback {
        fun onRecognizingTextFinish(recognizedTextList: List<RecognizedText>)
    }

    init {
        preparePhotoForRecognition()
    }

    private fun preparePhotoForRecognition() {
        val uri = Uri.parse(photoPath)
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)

        imageFrame = Frame.Builder()
                .setBitmap(bitmap)
                .build()
    }

    fun startTextRecognizing() {
        val textRecognizer = TextRecognizer.Builder(context).build()
        val textBlocks = textRecognizer.detect(imageFrame)

        for (i in 0 until textBlocks.size()) {
            val textBlock = textBlocks.get(textBlocks.keyAt(i))
            divideRecognizedTextToLine(textBlock.value)
        }

        callback.onRecognizingTextFinish(recognizedTextList)
    }

    private fun divideRecognizedTextToLine(text: String?) {
        val textList = text?.lines()

        for (it in textList!!) {
            val recognizedText = RecognizedText(it, CardFieldTag.UNKNOWN)
            recognizedTextList.add(recognizedText)
        }
    }


}