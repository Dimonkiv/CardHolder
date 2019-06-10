package com.dimonkiv.cardscanner.data.model

import android.os.Bundle
import com.dimonkiv.cardscanner.utill.FragmentById

class FragmentData(private val fragmentById: FragmentById) {
    private var bundle: Bundle? = null

    constructor(fragmentById: FragmentById, bundle: Bundle): this(fragmentById) {
        this.bundle = bundle
    }

    fun getBundle(): Bundle? {
        return bundle
    }

    fun getFragmentById(): FragmentById {
        return fragmentById
    }
}