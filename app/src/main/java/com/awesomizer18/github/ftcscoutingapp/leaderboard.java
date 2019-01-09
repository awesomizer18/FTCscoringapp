package com.awesomizer18.github.ftcscoutingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class leaderboard extends AppCompatActivity {

    private String title1;
    private String text1;

    ListView lv;

//    String Test[] = {
//            "Team Number: " + Integer.toString(MainActivity.teamNumber) + "Match Number" + Integer.toString(MainActivity.matchNumber) + "    Total Score: " + Integer.toString(MainActivity.totalScore)
//    };

    String Data[] = {
            "Team Number: " + title1 + "Total Score: " + text1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        lv = findViewById(R.id.Lview);

        loadFile(lv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(leaderboard.this, android.R.layout.simple_list_item_1, Data);
        lv.setAdapter(adapter);
//        setupBackButton();
        //working
        setupRankingSystem();


    }

    private void setupRankingSystem() {
    }

    public void loadFile(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(MainActivity.FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            String title;
            String text;
            while ((title = br.readLine()) != null) {
                sb.append(title).append("\n");
            }
            while ((text = br.readLine()) != null) {
                sb2.append(text).append("\n");
            }
            title1 = sb.toString();
            text1 = sb2.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    private void setupBackButton() {
//        Button back = findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, leaderboard.class);
    }
}
