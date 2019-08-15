package com.example.gallery.mvvm.contact

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gallery.R
import com.example.gallery.adapters.ContactAdapter
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactViewModel

    private var adapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        viewModel = ViewModelProviders.of(this)[ContactViewModel::class.java]

        initViews()
        LoadContactAsyncTask().execute()
    }


    /* -------------------------------------------------------------------------------------------*/
    /* Internal helpers */

    private fun initViews() {
        contact_list.layoutManager = LinearLayoutManager(this)
    }

    private fun showContacts() {
//        viewModel.contactList.observe(this, Observer {
//        })
        viewModel.readContact()
    }

    private inner class LoadContactAsyncTask : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            showContacts()
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            adapter = ContactAdapter(viewModel.contactList.value)
            contact_list.adapter = adapter
            super.onPostExecute(aVoid)
        }
    }

}