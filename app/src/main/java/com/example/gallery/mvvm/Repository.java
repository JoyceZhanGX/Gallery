package com.example.gallery.mvvm;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository instance;
    private ContentResolver resolver;

    private Repository(Context context) {
        // init
        resolver = context.getApplicationContext().getContentResolver();
    }

    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }
        return instance;
    }

    public Observable<List<String>> readContacts() {
        List<String> _contactList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    _contactList.add(number);
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return Observable.just(_contactList);
    }

    public Observable<List<String>> loadImages() {
        List<String> photoList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    photoList.add(data);
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return Observable.just(photoList);
    }
}
