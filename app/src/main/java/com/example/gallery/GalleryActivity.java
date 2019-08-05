package com.example.gallery;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    ArrayList<String> photoList = new ArrayList<>();

    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ArrayList<ImageUri> imageUrlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageView = (ImageView) findViewById(R.id.imageView);
        recyclerView = (RecyclerView) findViewById(R.id.photoView);
        gridLayoutManager = new GridLayoutManager(GalleryActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                loadImages();
            }
        });

        ImageAdapter dataAdapter = new ImageAdapter(getApplicationContext(), photoList);

        recyclerView.setAdapter(dataAdapter);
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
