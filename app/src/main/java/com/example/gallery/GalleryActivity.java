package com.example.gallery;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gallery.adapters.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private List<String> photoList = new ArrayList<>();

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ImageAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initViews();

        new LoadImageAsyncTask().execute();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.photoView);
        gridLayoutManager = new GridLayoutManager(GalleryActivity.this, 3);
        recyclerView = findViewById(R.id.photoView);
    }

    private void loadImages() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    photoList.add(data);
                }
            }

            dataAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private class LoadImageAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            loadImages();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dataAdapter = new ImageAdapter( photoList);
            recyclerView.setAdapter(dataAdapter);
            super.onPostExecute(aVoid);
        }
    }
}
