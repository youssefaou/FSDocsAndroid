package com.zouag.fsdocs.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.ui.fragments.student.StudentFrag1;
import com.zouag.fsdocs.ui.fragments.student.StudentFrag2;
import com.zouag.fsdocs.ui.fragments.student.StudentFrag3;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudentActivity extends AppCompatActivity implements
        StudentFrag1.FirstFragListener,
        StudentFrag2.SecondFragListener,
        StudentFrag3.ThirdFragListener {

    private JSONObject mUser; // the details of the user currently registering
    private boolean isSignin = true;

    @Bind(R.id.viewSwitcher)
    ViewSwitcher mViewSwitcher;
    @Bind(R.id.signinLabel)
    TextView mSigninTextview;
    @Bind(R.id.signupLabel)
    TextView mSignupTextview;
    @Bind(R.id.switchLabel)
    TextView switchLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        ButterKnife.bind(this); // Butterknife's setup

        mSigninTextview.setOnClickListener(v -> {
            if (!isSignin) {
                mViewSwitcher.showPrevious();
                isSignin = true;
                mSignupTextview.setBackgroundColor(getResources().getColor(R.color.inactiveTab));
                mSigninTextview.setBackgroundColor(getResources().getColor(R.color.activeTab));
            }
        });
        mSignupTextview.setOnClickListener(v -> {
            if (isSignin) {
                mViewSwitcher.showNext();
                isSignin = false;
                mSignupTextview.setBackgroundColor(getResources().getColor(R.color.activeTab));
                mSigninTextview.setBackgroundColor(getResources().getColor(R.color.inactiveTab));
            }
        });

        switchLabel.setOnClickListener(v -> {
            Intent intent = new Intent(StudentActivity.this, TeacherActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        displayFragment(0);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof StudentFrag2) {
            displayFragment(0);
        } else if (fragment instanceof StudentFrag3) {
            displayFragment(1);
        } else
            super.onBackPressed();
    }

    private void displayFragment(int position) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (position) {
            case 0:
                fragment = new StudentFrag1();
                ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                break;
            case 1:
                fragment = new StudentFrag2();
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
            case 2:
                fragment = new StudentFrag3();
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
        }

        ft.replace(R.id.fragment_container, fragment, "fragment" + position);
        ft.commit();
    }

    @Override
    public void navigateToSecondFrag(String firstname, String lastname) {
        mUser = new JSONObject();
        try {
            mUser.put("firstname", firstname);
            mUser.put("lastname", lastname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayFragment(1);
    }

    @Override
    public void navigateToThirdFrag(String cne, String date) {
        assert mUser != null;

        try {
            mUser.put("cne", cne);
            mUser.put("date", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayFragment(2);
    }

    @Override
    public void confirmSignup(String email, String password) {
        try {
            mUser.put("email", email);
            mUser.put("password", password);

            throw new IOException("test");

        } catch (JSONException | IOException e) {
            e.printStackTrace();

            System.out.println(mUser.toString());
        }
    }
}
