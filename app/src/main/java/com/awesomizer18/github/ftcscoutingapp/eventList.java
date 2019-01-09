/*
package com.awesomizer18.github.ftcscoutingapp;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class eventList extends ListActivity {
    private static String FILE_NAME = "";
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
//    ArrayList<String> listItems = new ArrayList<>();
//
//    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
//    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter = 0;

    ListView lv;



    public void readSavedFile() {
        FileInputStream fis = null;
            try {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String title;

                while ((title = br.readLine()) != null) {
                    sb.append(title).append("\n");
                }

                String text;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    String Test[] = {
            "First", "Second", "Third"
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_leaderboard);
        lv = (ListView) findViewById(R.id.list1);
//        adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, listItems);
//        setListAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(eventList.this, android.R.layout.simple_list_item_1);
        ListView.setAdapter(adapter);

        buttonTest();
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION

    public void buttonTest() {
        Button testBtn = findViewById(R.id.addBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItems.add("Test");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
*/