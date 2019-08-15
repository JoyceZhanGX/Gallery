package com.example.gallery.mvvm.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.AppApplication
import io.reactivex.disposables.CompositeDisposable

class GalleryViewModel : ViewModel() {

    private val apiTasks = CompositeDisposable()

    private var _photoList = MutableLiveData<List<String>>()
    val photoList: LiveData<List<String>>
        get() = _photoList

    fun readPhotos() {
        apiTasks.add(
            AppApplication.repository.loadImages()
                .subscribe {
                    _photoList.postValue(it)
                })
    }
}