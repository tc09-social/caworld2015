package com.ca.cheyi02.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;


public class DisplayCoursesActivity extends Activity {
    private static final String TAG = DisplayCoursesActivity.class.getName();

    int imageRID(String imageName) {
        if (imageName.equals("three-com-1")) return R.drawable.threecom1;
        else if (imageName.equals("three-com-2")) return R.drawable.threecom2;
        return R.drawable.threecom3;
    }

    void addOneCourse(String id, String name, String image) {

        LinearLayout container = (LinearLayout) findViewById(R.id.list_container);
        View view = getLayoutInflater().inflate(R.layout.course, container, false);
        container.addView(view);

        ViewGroup viewGroup =(ViewGroup) ((ViewGroup) view).getChildAt(0);
        TextView tv1 = (TextView) viewGroup.getChildAt(1);
        TextView tv2 = (TextView) viewGroup.getChildAt(2);
        ImageView iv = (ImageView) viewGroup.getChildAt(0);

        tv1.setText(id);
        tv2.setText(name);
        iv.setImageResource(imageRID(image));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_courses);

        Intent intent = getIntent();
        String usernameString = intent.getStringExtra(MyActivity.EXTRA_USERNAME);
        try {
            JSONObject jsonObject = new JSONObject(usernameString);
            String firstNameString = jsonObject.getString("firstName");
            TextView set_usernameText = (TextView) findViewById(R.id.set_username_message);
            set_usernameText.setText(firstNameString);
            JSONObject regCourse = jsonObject.getJSONObject("regCourses");
            JSONArray courses = regCourse.getJSONArray("course");
            for (int i=0; i < courses.length(); ++i) {
                JSONObject course = courses.getJSONObject(i);
                String idString = course.getString("id");
                String nameString = course.getString("name");
                String imageString = course.getString("image");
                addOneCourse(idString, nameString, imageString);
            }
        } catch (Exception e) {
            Log.d(TAG, "exception", e);
        }
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
