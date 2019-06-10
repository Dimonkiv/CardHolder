package com.dimonkiv.cardscanner.ui.modules.main.cardrecognition

import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.db.dao.RecognizedText
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.utill.CardFieldTag
import com.dimonkiv.cardscanner.utill.TextRecognitionHelper

class CardRecognitionPresenter(private val fragment: CardRecognitionFragment) : CardRecognitionContract.Presenter,
        TextRecognitionHelper.Callback {


    private lateinit var view: CardRecognitionView
    private lateinit var db: AppDatabase

    private val card = BusinessCard()
    private val categoryList = ArrayList<Category>()
    private val recognizedTextList = ArrayList<RecognizedText>()

    private var isShowRecognitionFields = true
    private var isAddToContactSelected = false
    private var photoPath = ""


    /*----------------------------------------------Initialization----------------------------------------------------*/
    init {
        initDB()
        loadData()
    }

    override fun setView(view: CardRecognitionView) {
        this.view = view
        startTextRecognition()
        showInitialData()
    }

    override fun setPhotoPath(photoPath: String) {
        this.photoPath = photoPath
    }

    private fun initDB() {
        db = InitDatabase.getInstance(fragment.context!!).getDatabase()
    }


    /*-----------------------------------------------Work with DB-----------------------------------------------------*/
    private fun loadData() {
        loadCategories()
    }

    private fun loadCategories() {
        val categoryDAO = db.categoryDao()
        categoryList.addAll(categoryDAO.getAll())

        card.categoryId = categoryList[0].id
    }

    private fun createCard() {
        val businessCardDAO = db.businessCardDao()
        businessCardDAO.insert(card)
    }


    /*--------------------------------------Show initial data-------------------------------------*/
    private fun showInitialData() {
        showCategories()
    }

    private fun showCategories() {
        view.showCategories(categoryList)
    }

    private fun showRecognizedText() {
        view.showRecognizedTextList(recognizedTextList)
    }


    /*------------------------------------Listeners-----------------------------------------------*/
    override fun onBackButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onDoneButtonClick() {
        createCard()

        if(isAddToContactSelected && fragment.checkPermission()){
            addCardToContact()
        }
        view.showMessage("Візитка успішно створена!")
    }

    override fun onHideRecognitionFieldsButtonClick() {
        if (isShowRecognitionFields) {
            view.hideRecognitionFields()
        } else {
            view.showRecognitionFields()
        }

        isShowRecognitionFields = !isShowRecognitionFields
    }

    override fun onHideRecognitionContainerButtonClick() {
        view.hideRecognitionContainer()
    }

    override fun onNameTextChanged(name: String) {
        card.name = name
    }

    override fun onPhoneTextChanged(phone: String) {
        card.phone = phone
    }

    override fun onEmailTextChanged(email: String) {
        card.email = email
    }

    override fun onAddressTextChanged(address: String) {
        card.location = address
    }

    override fun onSiteTextChanged(site: String) {
        card.site = site
    }

    override fun onAddToContactStateChange(isChecked: Boolean) {
        isAddToContactSelected = isChecked
    }

    override fun onCategoryItemSelected(pos: Int) {
        val categoryId = categoryList[pos].id
        card.categoryId = categoryId
    }

    override fun onFieldTagSelected(item: RecognizedText) {
        when (item.tag) {
            CardFieldTag.TITLE -> view.showName(item.title)

            CardFieldTag.ADDRESS -> view.showAddress(item.title)

            CardFieldTag.PHONE -> view.showPhone(item.title)

            CardFieldTag.EMEIL -> view.showEmail(item.title)

            CardFieldTag.SITE -> view.showSite(item.title)
        }
    }

    override fun onRecognizingTextFinish(recognizedTextList: List<RecognizedText>) {
        this.recognizedTextList.addAll(recognizedTextList)
        showRecognizedText()
        view.hideProgressBar()
    }


    /*--------------------------------------Methods for add contact to phone book-------------------------------------*/
    private fun addCardToContact() {
        //add empty contact
        val cv = ContentValues()
        val rawContactUri = fragment.context?.contentResolver?.insert(ContactsContract.RawContacts.CONTENT_URI, cv)
        val contactId = ContentUris.parseId(rawContactUri)

        insertContactDisplayName(contactId)
        insertPhoneNumber(contactId)
        insertEmail(contactId)
        insertWebsite(contactId)
    }

    private fun insertContactDisplayName(contactId: Long) {
        if (card.name.isNotEmpty()) {
            val cv = ContentValues()

            cv.put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            cv.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, card.name)

            fragment.context?.contentResolver?.insert(ContactsContract.Data.CONTENT_URI, cv)
        }
    }

    private fun insertPhoneNumber(contactId: Long) {
        if (card.phone.isNotEmpty()) {
            val cv = ContentValues()

            cv.put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, card.phone)
            cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)

            fragment.context?.contentResolver?.insert(ContactsContract.Data.CONTENT_URI, cv)
        }
    }

    private fun insertEmail(contactId: Long) {
        if (card.email.isNotEmpty()) {
            val cv = ContentValues()

            cv.put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
            cv.put(ContactsContract.CommonDataKinds.Email.ADDRESS, card.email)
            cv.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)

            fragment.context?.contentResolver?.insert(ContactsContract.Data.CONTENT_URI, cv)
        }
    }

    private fun insertWebsite(contactId: Long) {
        if (card.site.isNotEmpty()) {
            val cv = ContentValues()

            cv.put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)
            cv.put(ContactsContract.CommonDataKinds.Website.URL, card.site)
            cv.put(ContactsContract.CommonDataKinds.Website.TYPE, ContactsContract.CommonDataKinds.Website.TYPE_WORK)

            fragment.context?.contentResolver?.insert(ContactsContract.Data.CONTENT_URI, cv)
        }
    }


    /*--------------------------------------------------Others methods------------------------------------------------*/
    private fun startTextRecognition() {
        view.showProgressBar()
        val textRecognitionHelper = TextRecognitionHelper(photoPath, fragment.context!!, this)

        textRecognitionHelper.startTextRecognizing()
    }

    override fun checkCameraPermission(grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            addCardToContact()
        }
    }
}