package com.dimonkiv.cardscanner.ui.modules.main.cardrecognition

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.*
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.utill.FragmentById

class CardRecognitionFragment: Fragment(), CardRecognitionContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: CardRecognitionPresenter
    private lateinit var view: CardRecognitionView

    companion object {
        private const val PHOTO_PATH = "photoPath"
        private const val PHONE_REQUEST_CODE = 100

        fun getBundle(photoPath: String): Bundle {
            val bundle = Bundle()
            bundle.putString(PHOTO_PATH, photoPath)

            return bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_card_recognition, container, false)

        initPresenter()
        resumeBundle()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CardRecognitionPresenter(this)
    }

    private fun initView() {
        view = CardRecognitionView(this, presenter, getMainActivity(), context!!, root)
    }

    private fun resumeBundle() {
        if (arguments != null && arguments!!.containsKey(PHOTO_PATH)) {
            presenter.setPhotoPath(arguments?.getString(PHOTO_PATH)!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        view.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

    override fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    override fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity?.checkSelfPermission(Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.WRITE_CONTACTS), PHONE_REQUEST_CODE)
                return false
            }
        }

        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PHONE_REQUEST_CODE) {
            presenter.checkCameraPermission(grantResults)
        }
    }

}