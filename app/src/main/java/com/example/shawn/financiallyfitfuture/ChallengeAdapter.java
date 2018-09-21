package com.example.shawn.financiallyfitfuture;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import android.util.*;
import android.widget.TextView;

public class ChallengeAdapter extends ArrayAdapter<JSONObject> {
    JSONObject[] jsons;
    Activity context;
    public ChallengeAdapter(Activity context, JSONObject[] arr){
        super(context, R.layout.list_layout, arr);
        this.context=context;
        jsons=arr;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_layout, null, true);
        TextView title = (TextView) rowView.findViewById(R.id.card_text);
        TextView desc = (TextView) rowView.findViewById(R.id.card_desc);
        try{
            title.setText(jsons[position].getString("name"));
            String s = "";
            JSONArray jrr = jsons[position].getJSONArray("description");
            for(int x=0; x<jrr.length(); x++){
                s+=jrr.getString(x)+"\n";
            }
            desc.setText(s);
        }
        catch(JSONException e){
            e.printStackTrace();

        }

        return rowView;

    }
}
