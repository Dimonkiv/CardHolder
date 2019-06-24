package com.dimonkiv.cardscanner.ui.modules.main.cardrecognition

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.db.dao.RecognizedText
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.ui.adapter.CardRecognizedAdapter
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity

class CardRecognitionView(private val fragment: CardRecognitionFragment,
                          private val presenter: CardRecognitionPresenter,
                          private val activity: MainActivity,
                          private val context: Context,
                          private val view: View) : CardRecognitionContract.View,
        CardRecognizedAdapter.Callback {

    private lateinit var toolbar: Toolbar
    private lateinit var categorySpinner: Spinner
    private lateinit var addToContactCHB: CheckBox
    private lateinit var nameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var emailET: EditText
    private lateinit var addressET: EditText
    private lateinit var siteET: EditText
    private lateinit var recognitionContainerLL: LinearLayout
    private lateinit var hideIB: ImageButton
    private lateinit var hideRecognitionIB: ImageButton
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: CardRecognizedAdapter

    init {
        initUI()
        initToolbar()
        initAdapter()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        categorySpinner = view.findViewById(R.id.category_spinner)
        addToContactCHB = view.findViewById(R.id.checkbox)
        nameET = view.findViewById(R.id.nameET)
        phoneET = view.findViewById(R.id.phoneET)
        emailET = view.findViewById(R.id.emailET)
        addressET = view.findViewById(R.id.addressET)
        siteET = view.findViewById(R.id.siteET)
        recognitionContainerLL = view.findViewById(R.id.recognition_container_ll)
        hideIB = view.findViewById(R.id.arrow_ib)
        hideRecognitionIB = view.findViewById(R.id.cancel_recognition_ib)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = "Редагування візитки"
        fragment.setHasOptionsMenu(true)

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            presenter.onBackButtonClick()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) {
        if (item?.itemId == R.id.done) {
            presenter.onDoneButtonClick()
        }
    }

    private fun initAdapter() {
        adapter = CardRecognizedAdapter(context, this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
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

        addToContactCHB.setOnCheckedChangeListener { _, isChecked ->
            presenter.onAddToContactStateChange(isChecked)
        }

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onCategoryItemSelected(position)
            }

        }

        hideIB.setOnClickListener {
            presenter.onHideRecognitionFieldsButtonClick()
        }

        hideRecognitionIB.setOnClickListener {
            presenter.onHideRecognitionContainerButtonClick()
        }
    }

    override fun onFieldTagSelected(item: RecognizedText) {
        presenter.onFieldTagSelected(item)
    }

    override fun showCategories(categoryList: List<Category>) {
        val adapter = ArrayAdapter<Category>(context, android.R.layout.simple_spinner_dropdown_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideRecognitionFields() {
        recyclerView.visibility = View.GONE
        hideIB.setImageResource(R.drawable.ic_arrow_top)
    }

    override fun showRecognitionFields() {
        recyclerView.visibility = View.VISIBLE
        hideIB.setImageResource(R.drawable.ic_arrow_bottom)
    }

    override fun hideRecognitionContainer() {
        recognitionContainerLL.visibility = View.GONE
    }

    override fun showRecognizedTextList(recognizedTextList: List<RecognizedText>) {
        adapter.setItems(recognizedTextList)
    }

    override fun showProgressBar() {
        activity.showProgressBar()
    }

    override fun hideProgressBar() {
        activity.hideProgressBar()
    }

    override fun showName(name: String) {
        nameET.setText(name)
    }

    override fun showEmail(email: String) {
        emailET.setText(email)
    }

    override fun showPhone(phone: String) {
        phoneET.setText(phone)
    }

    override fun showAddress(address: String) {
        addressET.setText(address)
    }

    override fun showSite(site: String) {
        siteET.setText(site)
    }
}