package com.example.gallery.mvvm.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.AppApplication
import io.reactivex.disposables.CompositeDisposable

class ContactViewModel : ViewModel() {

    private val apiTasks = CompositeDisposable()

    private var _contactList = MutableLiveData<List<String>>()
    val contactList: LiveData<List<String>>
        get() = _contactList

    fun readContact() {
        apiTasks.add(AppApplication.repository.readContacts()
            .subscribe {
            _contactList.postValue(it)
        })
    }
}