package com.ca.cheyi02.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;


public class DisplayCoursesActivity extends Activity implements Observer {
    private static final String TAG = DisplayCoursesActivity.class.getName();
    private CoursesListing mModel=null;
    private TextView mUsernameText=null;
    private LinearLayout mContainer=null;

    int imageRID(String imageName) {
        if (imageName.equals("three-com-1")) return R.drawable.threecom1;
        else if (imageName.equals("three-com-2")) return R.drawable.threecom2;
        return R.drawable.threecom3;
    }

    void addOneCourse(String id, String name, String image) {

        View view = getLayoutInflater().inflate(R.layout.course, mContainer, false);
        mContainer.addView(view);

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

        mModel = new CoursesListing(this);
        mModel.addObserver(this);
        mUsernameText = (TextView) findViewById(R.id.set_username_message);
        mContainer = (LinearLayout) findViewById(R.id.list_container);
        mUsernameText.setText("Loading ... ");
        mModel.getCoursesListing();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mModel != null) {
            mModel.processPendingRequest();
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        if (mUsernameText != null) {
            mUsernameText.setText(mModel.getFirstName());
        }
        if (mContainer != null) {
            for (int i = 0; i < mModel.getCoursesLength(); ++i)
                addOneCourse(mModel.getCourseFieldAtIndex(i, "id"),
                        mModel.getCourseFieldAtIndex(i, "name"),
                        mModel.getCourseFieldAtIndex(i, "image"));
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
