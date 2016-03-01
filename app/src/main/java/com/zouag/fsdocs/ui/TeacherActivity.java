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
import com.zouag.fsdocs.ui.fragments.teacher.TeacherFrag1;
import com.zouag.fsdocs.ui.fragments.teacher.TeacherFrag2;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeacherActivity extends AppCompatActivity
        implements TeacherFrag1.FirstFragListener,
        TeacherFrag2.SecondFragListener {

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
        setContentView(R.layout.activity_teacher_login);
        ButterKnife.bind(this);

        switchLabel.setOnClickListener(v ->
                startActivity(new Intent(TeacherActivity.this, StudentActivity.class)));

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

        displayFragment(0);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof TeacherFrag2) {
            displayFragment(0);
        } else
            super.onBackPressed();
    }

    private void displayFragment(int position) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (position) {
            case 0:
                fragment = new TeacherFrag1();
                ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                break;
            case 1:
                fragment = new TeacherFrag2();
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
        }

        ft.replace(R.id.fragment_container, fragment, "fragment" + position);
        ft.commit();
    }

    @Override
    public void navigateToSecondFrag(String firstname, String lastname, String specialty) {
        mUser = new JSONObject();
        try {
            mUser.put("firstname", firstname);
            mUser.put("lastname", lastname);
            mUser.put("specialty", specialty);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayFragment(1);
    }

    @Override
    public void confirmSignup(String email, String password) {

    }
}
