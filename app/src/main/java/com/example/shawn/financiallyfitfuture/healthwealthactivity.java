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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;

public class healthwealthactivity extends AppCompatActivity {
    ArrayList<JSONObject> healthChallenges = new ArrayList<JSONObject>();
    ArrayList<JSONObject> wealthChallenges = new ArrayList<JSONObject>();
    ArrayList<JSONObject> rewardChallenges = new ArrayList<JSONObject>();
    private TextView mTextMessage;
    private ListView list;
    ChallengeAdapter hadapter;
    ChallengeAdapter wadapter;
    ChallengeAdapter radapter;
    private FloatingActionButton fab;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_health: {

                   displayH();

                    return true;
                }
                case R.id.navigation_wealth: {

                    displayW();

                    return true;
                }
                case R.id.navigation_rewards:{
                    displayR();

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
                    healthChallenges.add(obj);
                }
                else if(obj.getString("type").equals("Wealth")){
                    wealthChallenges.add(obj);
                }
                else if(obj.getString("type").equals("Reward")){
                    rewardChallenges.add(obj);
                }

            }
        }
        catch(JSONException e){
            e.printStackTrace();

        }
       // mTextMessage.setText(LoadJSON(getApplicationContext()));
        list = (ListView) findViewById(R.id.list);
        //list.setAdapter(new ArrayAdapter<>());
        hadapter = new ChallengeAdapter(this, healthChallenges.toArray(new JSONObject[healthChallenges.size()]));
        wadapter = new ChallengeAdapter(this, wealthChallenges.toArray(new JSONObject[wealthChallenges.size()]));
        radapter = new ChallengeAdapter(this, rewardChallenges.toArray(new JSONObject[rewardChallenges.size()]));
        if(getIntent().getIntExtra("REWARD",0)==1)
            navigation.setSelectedItemId(R.id.navigation_rewards);
        else
            displayH();





    }

    public void displayH(){
        list.setAdapter(hadapter);
        hadapter.notifyDataSetChanged();

    }
    public void displayW(){
        list.setAdapter(wadapter);
        wadapter.notifyDataSetChanged();


    }
    public void displayR(){
        list.setAdapter(radapter);
        radapter.notifyDataSetChanged();


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
