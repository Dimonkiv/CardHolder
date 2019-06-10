package com.dimonkiv.cardscanner.ui.modules.main.createcard

import android.content.Context
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity

class CreateCardView(private val fragment: CreateCardFragment,
                     private val presenter: CreateCardPresenter,
                     private val activity: MainActivity,
                     private val view: View,
                     private val context: Context) : CreateCardContract.View{
    private lateinit var toolbar: Toolbar
    private lateinit var categorySpinner: Spinner
    private lateinit var addToPhoneBookCHB: CheckBox
    private lateinit var nameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var emailET: EditText
    private lateinit var addressET: EditText
    private lateinit var siteET: EditText

    init {
        initUI()
        initToolbar()
        setListeners()
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        categorySpinner = view.findViewById(R.id.category_spinner)
        addToPhoneBookCHB = view.findViewById(R.id.checkbox)
        nameET = view.findViewById(R.id.nameET)
        phoneET = view.findViewById(R.id.phoneET)
        emailET = view.findViewById(R.id.emailET)
        addressET = view.findViewById(R.id.addressET)
        siteET = view.findViewById(R.id.siteET)
    }


    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = "Створити візитку"
        fragment.setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar_edit, menu)
    }

    private fun setListeners() {
        nameET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onNameTextChanged(s.toString())
            }

        })

        phoneET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onPhoneTextChanged(s.toString())
            }

        })

        emailET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onEmailTextChanged(s.toString())
            }

        })

        addressET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onAddressTextChanged(s.toString())
            }

        })

        siteET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onSiteTextChanged(s.toString())
            }

        })

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.done) {
            presenter.onAddButtonClick()
        }

        return true
    }

    override fun showCategories(categoryList: List<Category>) {
        val adapter = ArrayAdapter<Category>(context, android.R.layout.simple_spinner_dropdown_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter
    }
}