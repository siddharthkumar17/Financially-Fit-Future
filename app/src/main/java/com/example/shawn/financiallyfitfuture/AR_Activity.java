package com.example.shawn.financiallyfitfuture;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.*;
import com.google.ar.sceneform.ux.*;

import java.util.concurrent.CompletableFuture;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AR_Activity extends AppCompatActivity {
   // private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
//    private ModelRenderable andyRenderable;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);
        arFragment.getPlaneDiscoveryController().hide();
        arFragment.getPlaneDiscoveryController().setInstructionView(null);

        AnchorNode node = new AnchorNode();
        node.setParent(arFragment.getArSceneView().getScene());
        Random random = new Random();

        //node.setLocalPosition(new Vector3(5,5, 5 ));

        new SpawnTask().execute();
            //node.setRenderable(ViewRenderable.builder().setView(getApplicationContext(), R.layout.test_view).build().join());

        // CompletableFuture<ViewRenderable> future = ViewRenderable.builder().setView(getApplicationContext(), R.layout.test_view).build();




    }
    public void spawn(){
        ViewRenderable.builder().setView(this, R.layout.test_view).build()
                .thenAccept(viewRenderable -> {
                    Node noteText = new Node();
                    noteText.setParent(arFragment.getArSceneView().getScene());
                    //noteText.setParent(postitNode);
                    noteText.setRenderable(viewRenderable);

                    //set the note position in relation to its parent node.
                    // In this case, along the y axis of the Post-It note
                    noteText.setWorldPosition(new Vector3(0.5f, -0.05f, 0f));
                    // noteText.setWorldRotation();
                });
    }
    class SpawnTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
           // while(true) {
                Random random = new Random();
                try {
                    Thread.sleep(random.nextInt(5000) + 10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }

           // }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            spawn();
        }
    }


}
