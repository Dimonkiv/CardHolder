package com.dimonkiv.cardscanner.data

class TempLocalStorage {

    private var selectedImageId: Int? = null

    companion object {
        private var instance: TempLocalStorage? = null

        fun getInstance(): TempLocalStorage {
            if (instance == null) {
                instance = TempLocalStorage()
            }

            return instance!!
        }
    }

    fun getSelectedImageId(): Int? {
        return selectedImageId
    }

    fun setSelectedImageId(selectedImageId: Int?) {
        this.selectedImageId = selectedImageId
    }
}