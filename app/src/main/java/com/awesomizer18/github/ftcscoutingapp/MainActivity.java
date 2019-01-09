package com.awesomizer18.github.ftcscoutingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public int teamMarker;
    public static int totalScore;
    public int land;
    public int sample;
    public int autoPark;
    public int landerMinerals;
    public int depotMinerals;
    public int deScoreAbility;
    public int isLatched;
    public static int teamNumber;
    public static int matchNumber;

    private boolean matchNumberComplete;
    private boolean teamNumberComplete;
    private boolean depotMineralsComplete;
    private boolean landerMineralsComplete;

    public static String FILE_NAME = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //idea for save button to create a new entry in leaderboard
//        final TextView textView = new TextView(context);
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        ); // set height and width
//        textView.setMargins(left, top, right, bottom); to set margins if necessary
//        textView.setLayoutParams(layoutParams);

        setupSaveButton();

        setupMenuButton();
        //working

        //autonomous setup
        setupLandSwitch();
        setupSampleSwitch();
        setupTeamMarkerSwitch();
        setupAutoParkSwitch();
        //working

        //TeleOp setup
        ableToDescore();
        setupLatchedSwitch();
        //working
    }

    private void calculateTotal() {
        int landerScore = 4;
        int depotScore = 2;
        totalScore = land + sample + teamMarker + autoPark + (landerMinerals * landerScore) + (depotMinerals * depotScore) + deScoreAbility + isLatched;
        String strTitle = new String("Total Score: ");
        TextView totalpoints = findViewById(R.id.totalScore);
        totalpoints.setText(strTitle + Integer.toString(totalScore));
        checkForCompletion();
        setupDepotMineralsInput();
        setupLanderMineralsInput();
    }

    public void setupSaveButton() {
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Debug message: ", Toast.LENGTH_LONG).show();
                calculateTotal();
//                Toast.makeText(getApplicationContext(), "Debug message: calculate total", Toast.LENGTH_LONG).show();
                getTeamNumber();
//                Toast.makeText(getApplicationContext(), "Debug message: get team number", Toast.LENGTH_LONG).show();
                getMatchNumber();
//                Toast.makeText(getApplicationContext(), "Debug message: get match number", Toast.LENGTH_LONG).show();
                saveFile(v);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
//                writeFileOnInternalStorage();
                //output the total to leaderboard where it saves and ranks them

                //save the data to a list in the leaderboard
            }
        });
    }

    public static Intent makeIntent2(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public void setupMenuButton() {
        Button menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //end the activity
                finish();
            }
        });
    }

    public void setupAutoParkSwitch() {
        Switch parked = findViewById(R.id.park);
        parked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    autoPark = 15;
                }
                else {
                    autoPark = 0;
                }
            }
        });
    }

    public void setupLandSwitch() {
        Switch landed = findViewById(R.id.land);
        landed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    land = 50;
                }
                else {
                    land = 0;
                }

            }
        });
    }

    public void setupSampleSwitch() {
        Switch sampled = findViewById(R.id.sample);
        sampled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    sample = 25;
                }
                else {
                    sample = 0;
                }
            }
        });
    }

    public void setupTeamMarkerSwitch() {
        Switch tm = findViewById(R.id.tm);
        tm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    teamMarker = 15;
                }
                else {
                    teamMarker = 0;
                }
            }
        });
    }

    public void setupLanderMineralsInput(){
        EditText landerminerals = findViewById(R.id.landerminerals);
        if(TextUtils.isEmpty(landerminerals.getText())) {
            landerminerals.setError("An integer is required!");
            landerMineralsComplete = false;
        }
        else {
            String sTextFromET = landerminerals.getText().toString();
            landerMinerals = new Integer(sTextFromET).intValue();
            landerMineralsComplete = true;
        }
    }

    public void setupDepotMineralsInput(){
        EditText depotminerals = findViewById(R.id.depotminerals);
        if (TextUtils.isEmpty(depotminerals.getText())) {
            depotminerals.setError("An integer is required!");
            depotMineralsComplete = false;
        }
        else {
            String sTextFromET = depotminerals.getText().toString();
            depotMinerals = new Integer(sTextFromET).intValue();
            depotMineralsComplete = true;
        }
    }

    public void getTeamNumber() {
        EditText teamnumber = findViewById(R.id.teamnumber);
        if (TextUtils.isEmpty(teamnumber.getText())) {
            teamnumber.setError("Please enter a team number");
            teamNumberComplete = false;
        }
        else {
            String sTextFromET = teamnumber.getText().toString();
            teamNumber = new Integer(sTextFromET).intValue();
            teamNumberComplete = true;
            //put teamNumber in shared preferences below
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Team Number", Integer.toString(teamNumber));
            editor.apply();
        }
    }

    public void getMatchNumber() {
        EditText matchnumber = findViewById(R.id.matchnumber);
        if (TextUtils.isEmpty(matchnumber.getText())) {
            matchnumber.setError("Please enter a match number");
            matchNumberComplete = false;
        }
        else {
            String sTextFromET = matchnumber.getText().toString();
            matchNumber = new Integer(sTextFromET).intValue();
            matchNumberComplete = true;
            //            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences preferences = getSharedPreferences("Match number", Context .MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("Match Number", Integer.toString(matchNumber));
//            editor.apply();
        }
    }

    public void ableToDescore() {
        Switch descore = findViewById(R.id.descore);
        descore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    deScoreAbility = 15;
                }
                else {
                    deScoreAbility = 0;
                }
            }
        });
    }

    public void setupLatchedSwitch() {
        Switch latch = findViewById(R.id.latch);
        latch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    isLatched = 50;
                }
                else {
                    isLatched = 0;
                }
            }
        });
    }

    public void checkForCompletion() {
        if (teamNumberComplete && matchNumberComplete && landerMineralsComplete && depotMineralsComplete) {
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(getApplicationContext(), "Debug message", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Please complete the required fields before saving")
                    .setIcon(R.drawable.error_icon)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
    }

    public void saveFile(View v) {
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = Integer.toString(teamNumber);
                String text = Integer.toString(totalScore) + " " + Integer.toString(matchNumber);
                FILE_NAME = Integer.toString(teamNumber) + Integer.toString(matchNumber);
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(title.getBytes());
                    fos.write(text.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

//    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
//        File file = new File(mcoContext.getFilesDir(),"mydir");
//        if(!file.exists()){
//            file.mkdir();
//        }
//
//        try{
//            File gpxfile = new File(file, sFileName);
//            FileWriter writer = new FileWriter(gpxfile);
//            writer.append(sBody);
//            writer.flush();
//            writer.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
