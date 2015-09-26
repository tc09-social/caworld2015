package com.ca.cheyi02.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

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

        /*
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Number"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Description"/>
        <ImageView
            android:layout_width="match_parent"
            android:layout_height="@dimen/image_height"
            android:scaleType="fitXY"
            android:src="@drawable/threecom2"/>
         */
        TextView tv;

        tv = new TextView(this);
        tv.setText(id);
        container.addView(tv);

        tv = new TextView(this);
        tv.setText(name);
        container.addView(tv);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView iv;
        iv = new ImageView(this);
        iv.setLayoutParams(lp);
        iv.setImageResource(imageRID(image));
        iv.getLayoutParams().height = (int) getResources().getDimension(R.dimen.image_height);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(iv);

    }


    void addCourseList() {

        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
        addOneCourse("1001", "CA Identity Suite 101", "three-com-1");
        addOneCourse("1002", "CA Identity Suite 102", "three-com-2");
        addOneCourse("1003", "CA Identity Suite 103", "three-com-3");
        addOneCourse("1004", "CA Identity Suite 104", "three-com-1");
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
//        addCourseList();
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
