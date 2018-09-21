package com.example.shawn.financiallyfitfuture;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.ar.sceneform.rendering.*;
import com.google.ar.sceneform.ux.*;

public class AR_Activity extends AppCompatActivity {
   // private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
//    private ModelRenderable andyRenderable;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);





    }


}
