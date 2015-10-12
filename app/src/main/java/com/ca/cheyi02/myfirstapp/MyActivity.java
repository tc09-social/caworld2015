package com.ca.cheyi02.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

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

        @Override
        protected String doInBackground(String... urls) {
            return RetrieveLink(urls[0]);
        }

        protected void onPostExecute(String result) {
            Log.d("Download", result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button button = (Button)findViewById(R.id.loginButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = null;
                try {
                    result = new RetrieveLinkTask().execute(getResources().getString(R.string.course_end_point)).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), DisplayCoursesActivity.class);

                intent.putExtra(EXTRA_USERNAME, result);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        });
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
