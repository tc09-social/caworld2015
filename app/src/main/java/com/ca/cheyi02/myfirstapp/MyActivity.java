package com.ca.cheyi02.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyActivity extends Activity {
    public final static String EXTRA_USERNAME = "com.ca.cheyi02.myfirstapp.USERNAME";
    private static final String TAG = MyActivity.class.getName();

    private String convertInputStreamToString (InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        if (null != inputStream) {
            inputStream.close();
        }
        return result;
    }

    private String RetrieveLink(String urlString) {
        InputStream inStream = null;
        HttpURLConnection urlConnection = null;
        Integer result=0;
        String response="";

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            // optional request header
//            urlConnection.setRequestProperty("Content-Type", "text/html");
//            urlConnection.setRequestProperty("Accept", "text/html");

            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                inStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inStream);
            }
        } catch (Exception e) {
            Log.d(TAG, "exception", e);
        }
        return response;
    }

    private class RetrieveLinkTask extends AsyncTask <String, String, String> {
        Context context;
        private RetrieveLinkTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... urls) {
            return RetrieveLink(urls[0]);
        }

        protected void onPostExecute(String result) {
            Intent intent = new Intent(context, DisplayCoursesActivity.class);

            intent.putExtra(EXTRA_USERNAME, result);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            Log.d("Download", result);
        }
    }

    public void login(View view) {
        new RetrieveLinkTask(this).execute(getResources().getString(R.string.course_end_point));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
