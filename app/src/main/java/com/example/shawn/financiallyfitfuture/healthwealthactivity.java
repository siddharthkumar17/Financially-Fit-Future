package com.example.shawn.financiallyfitfuture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import java.util.*;
import android.util.*;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class healthwealthactivity extends AppCompatActivity {
    ArrayList<String> healthChallenges = new ArrayList<String>();
    ArrayList<String> wealthChallenges = new ArrayList<String>();
    private TextView mTextMessage;
    private FloatingActionButton fab;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_health: {
                    String s ="";
                    for(String a: healthChallenges){
                        s+=a+'\n';
                    }
                    mTextMessage.setText(s);
                    return true;
                }
                case R.id.navigation_wealth: {
                    String s ="";
                    for(String a: wealthChallenges){
                        s+=a+'\n';
                    }
                    mTextMessage.setText(s);;
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthwealthactivity);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch unity
                Intent i = new Intent(getApplicationContext(), AR_Activity.class);
                startActivity(i);
            }
        });
        JSONObject json = null;
        try{
            json = new JSONObject(LoadJSON(getApplicationContext()));
            JSONArray challenges = json.getJSONArray("challenges");
            for(int x=0; x<challenges.length();x++){
                JSONObject obj = challenges.getJSONObject(x);
                if(obj.getString("type").equals("Health")){
                    healthChallenges.add(obj.toString(3));
                }
                else if(obj.getString("type").equals("Wealth")){
                    wealthChallenges.add(obj.toString(3));
                }

            }
        }
        catch(JSONException e){
            e.printStackTrace();

        }
       // mTextMessage.setText(LoadJSON(getApplicationContext()));

        displayH(getApplicationContext());


    }

    public void displayH(Context context){



    }
    public String LoadJSON(Context context){
        String json = null;
        try {
            InputStream is = context.getAssets().open("challenges.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
