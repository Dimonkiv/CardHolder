package com.dimonkiv.cardscanner.ui.modules.main.category

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image
import com.dimonkiv.cardscanner.ui.adapter.CategoryAdapter
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.ui.widgets.CircleIcon

class CategoryView(private val presenter: CategoryPresenter,
                   private val context: Context,
                   private val activity: MainActivity,
                   private val fragment: CategoryFragment,
                   private val view: View): CategoryContract.View {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var addCategoryLL: LinearLayout
    private lateinit var iconCI: CircleIcon

    private lateinit var adapter: CategoryAdapter


    /*------------------------------------Initialization------------------------------------------*/
    init {
        initUI()
        initToolbar()
        initAdapter()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recycler_view)
        addCategoryLL = view.findViewById(R.id.add_category_ll)
        iconCI = view.findViewById(R.id.icon_CI)
        iconCI.setIconDrawable(R.drawable.icon_add)
    }

    //toolbar
    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = context.getString(R.string.category)
        fragment.setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) {
        when(item?.itemId) {
            R.id.search_btn -> {}

            R.id.settings_btn -> {}
        }
    }

    private fun initAdapter() {
        adapter = CategoryAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
    /*----------------------------------------Listeners-------------------------------------------*/
    private fun setListeners() {
        addCategoryLL.setOnClickListener {

        }
    }

    override fun showCategories(categoryList: List<Category>, imageList: List<Image>) {
        adapter.setCategoryData(categoryList, imageList)
    }
}