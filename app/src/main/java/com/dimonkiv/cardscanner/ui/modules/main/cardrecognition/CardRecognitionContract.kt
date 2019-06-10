package com.dimonkiv.cardscanner.ui.modules.main.cardrecognition

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.dimonkiv.cardscanner.data.db.dao.RecognizedText
import com.dimonkiv.cardscanner.data.model.Category

interface CardRecognitionContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)

        fun showCategories(categoryList: List<Category>)

        fun showMessage(message: String)

        fun hideRecognitionFields()

        fun showRecognitionFields()

        fun hideRecognitionContainer()

        fun showRecognizedTextList(recognizedTextList: List<RecognizedText>)

        fun showProgressBar()

        fun hideProgressBar()

        fun showName(name: String)

        fun showEmail(email: String)

        fun showPhone(phone: String)

        fun showAddress(address: String)

        fun showSite(site: String)
    }

    interface Presenter {

        fun setView(view: CardRecognitionView)

        fun setPhotoPath(photoPath: String)

        fun onBackButtonClick()

        fun onDoneButtonClick()

        fun onNameTextChanged(name: String)

        fun onPhoneTextChanged(phone:  String)

        fun onEmailTextChanged(email: String)

        fun onAddressTextChanged(address: String)

        fun onSiteTextChanged(site: String)

        fun onAddToContactStateChange(isChecked: Boolean)

        fun onCategoryItemSelected(pos: Int)

        fun onFieldTagSelected(item: RecognizedText)

        fun onHideRecognitionFieldsButtonClick()

        fun onHideRecognitionContainerButtonClick()

        fun checkCameraPermission(grantResults: IntArray)
    }

    interface Fragment {
        fun showPreviousFragment()

        fun checkPermission(): Boolean
    }
}