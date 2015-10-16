package com.ca.cheyi02.myfirstapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.concurrent.ExecutionException;

public class CoursesListing extends Observable {
    private static final String TAG = MyActivity.class.getName();
    private String mJsonString=null;
    private String mErrorString="";
    private Context mContext;
    private String mFirstName=null;
    private JSONObject mRegCourse=null;
    JSONArray mCourses=null;

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

    private class RetrieveLinkTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            return RetrieveLink(urls[0]);
        }

        protected void onPostExecute(String result) {
            Log.d("Download", result);
        }
    }


    public CoursesListing(Context context) {
        mContext = context;
    }

    public void getCoursesListing() {
        Log.d("Download", mContext.getResources().getString(R.string.course_end_point));
        try {
            mJsonString = new RetrieveLinkTask().execute(mContext.getResources().getString(R.string.course_end_point)).get();
            JSONObject jsonObject = new JSONObject(mJsonString);
            mFirstName = jsonObject.getString("firstName");
            mRegCourse = jsonObject.getJSONObject("regCourses");
            mCourses = mRegCourse.getJSONArray("course");
        } catch (InterruptedException | ExecutionException | JSONException e) {
            mRegCourse = null;
            mFirstName = null;
            mErrorString = e.getMessage();
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    public String getFirstName() {
        return (mFirstName == null) ? mErrorString : mFirstName;
    }

    public int getCoursesLength() {
        return (mCourses == null) ? 0 : mCourses.length();
    }

    public String getCourseFieldAtIndex(int i, String field) {
        String theString;

        try {
            theString = mCourses.getJSONObject(i).getString(field);
        } catch (JSONException e) {
            theString = e.getMessage();
        }
        return theString;
    }
}
