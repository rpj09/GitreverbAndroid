package com.example.experimentalreverb;

import static com.example.experimentalreverb.R.id.LoadEmail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView tvLoadEmail = findViewById(LoadEmail);
//    TextView tvLoadName;
//    TextView tvLoadBio;
//    TextView tvLoadRepoCount;
   // Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //        tvLoadName = findViewById(R.id.tvLoadName);
//        tvLoadBio = findViewById(R.id.tvLoadBio);
//        tvLoadRepoCount = findViewById(R.id.tvLoadRepoCount);
        //btnLogout = findViewById(R.id.btnLogout);
        String username = getIntent().getStringExtra("username");
        String bio = getIntent().getStringExtra("bio");
        String repoCount = getIntent().getStringExtra("repoCount");
        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");
        String accessToken = getIntent().getStringExtra("accessToken");
        String photoUrl = getIntent().getStringExtra("photo");

        tvLoadEmail.setText(email);
//        tvLoadName.setText(name);
//        tvLoadBio.setText(bio);
//        tvLoadRepoCount.setText(repoCount);



    }
}