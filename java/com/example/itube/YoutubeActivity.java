package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class YoutubeActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button playButton, addButton, playlistButton;
    private DatabaseHelper dbHelper;
    private static final String TAG = "YoutubeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        urlEditText = findViewById(R.id.urlEditText);
        playButton = findViewById(R.id.playButton);
        addButton = findViewById(R.id.addButton);
        playlistButton = findViewById(R.id.playlistButton);
        dbHelper = new DatabaseHelper(this);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if (!url.isEmpty()) {
                    Log.i(TAG, "Playing URL: " + url);
                    Intent intent = new Intent(YoutubeActivity.this, PlayVideoActivity.class);
                    intent.putExtra("VIDEO_URL", url);
                    startActivity(intent);
                } else {
                    Toast.makeText(YoutubeActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if (!url.isEmpty()) {
                    Log.i(TAG, "Adding URL to playlist: " + url);
                    boolean isAdded = dbHelper.addVideoToPlaylist(url);
                    if (isAdded) {
                        Toast.makeText(YoutubeActivity.this, "Video added to playlist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(YoutubeActivity.this, "Failed to add video to playlist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YoutubeActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        playlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YoutubeActivity.this, PlaylistActivity.class);
                startActivity(intent);
            }
        });
    }
}
