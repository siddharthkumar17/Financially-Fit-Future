package com.example.shawn.financiallyfitfuture;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class WasteTimeTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute() {

    }
}
