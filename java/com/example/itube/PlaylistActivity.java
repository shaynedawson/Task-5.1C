package com.example.itube;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class PlaylistActivity extends AppCompatActivity {

    private ListView playlistListView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistListView = findViewById(R.id.playlistListView); // ListView for playlist
        dbHelper = new DatabaseHelper(this); // Database helper instance

        loadPlaylist(); // Load playlist from database
    }

    private void loadPlaylist() {
        Cursor cursor = dbHelper.getPlaylist(); // Get playlist from database
        ArrayList<String> playlist = new ArrayList<>();

        int urlIndex = cursor.getColumnIndex("url"); // Get index of URL column
        if (urlIndex != -1) {
            if (cursor.moveToFirst()) {
                do {
                    String url = cursor.getString(urlIndex); // Get URL from cursor
                    playlist.add(url); // Add URL to playlist
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(this, "No videos in playlist.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error retrieving playlist data.", Toast.LENGTH_SHORT).show();
        }
        cursor.close(); // Close cursor

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlist);
        playlistListView.setAdapter(adapter); // Set adapter for ListView
    }
}
