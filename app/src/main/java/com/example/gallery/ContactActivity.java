package com.example.gallery;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private List<String> contactList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        initViews();

        new LoadContactAsyncTask().execute();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void readContacts() {
        Cursor cursor = null;

        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactList.add(number);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private class LoadContactAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            readContacts();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter = new ContactAdapter(contactList);
            recyclerView.setAdapter(adapter);
            super.onPostExecute(aVoid);
        }
    }
}
