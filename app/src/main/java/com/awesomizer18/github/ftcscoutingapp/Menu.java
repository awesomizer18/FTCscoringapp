package com.awesomizer18.github.ftcscoutingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setupNewButton();

        setupYouTubeButton();

        setupOpenButton();

        setupDonateButton();

    }

    private void setupDonateButton() {
        Button donate = findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donate = donationtab.makeIntentForDonation(Menu.this);
                startActivity(donate);
            }
        });
    }

    private void setupOpenButton() {
        Button open = findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSaved = leaderboard.makeIntent(Menu.this);
                startActivity(viewSaved);
            }
        });
    }

    protected void setupYouTubeButton() {
        Button youtube = findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl( "https://www.youtube.com/awesomizer18/");
            }
        });
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void setupNewButton() {
        Button createNew = findViewById(R.id.createnew);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newSheet = MainActivity.makeIntent2(Menu.this);
                startActivity(newSheet);
            }
        });
    }
}
