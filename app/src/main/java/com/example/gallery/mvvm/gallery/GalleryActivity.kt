package com.example.gallery.mvvm.gallery

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.adapters.ImageAdapter
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    private lateinit var viewModel: GalleryViewModel
    private var adapter: ImageAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        viewModel = ViewModelProviders.of(this)[GalleryViewModel::class.java]

        initViews()
        LoadContactAsyncTask().execute()
    }

    private fun initViews() {
        gridLayoutManager = GridLayoutManager(this, 3)
        photoView.layoutManager = gridLayoutManager
    }

    private fun showPhoto() {
        viewModel.readPhotos()
    }

    private inner class LoadContactAsyncTask : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            showPhoto()
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            adapter = ImageAdapter(viewModel.photoList.value)
            photoView.adapter = adapter
            super.onPostExecute(aVoid)
        }
    }

}
