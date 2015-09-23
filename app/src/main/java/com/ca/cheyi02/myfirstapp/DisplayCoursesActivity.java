package com.ca.cheyi02.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;

public class DisplayCoursesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_courses);

        Intent intent = getIntent();
        String usernameString = intent.getStringExtra(MyActivity.EXTRA_USERNAME);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(usernameString);

        setContentView(textView);
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
