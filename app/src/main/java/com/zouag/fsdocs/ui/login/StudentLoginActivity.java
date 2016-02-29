package com.zouag.fsdocs.ui.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.ui.signup.fragments.StudentFrag1;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudentLoginActivity extends AppCompatActivity {

    private boolean isSignin = true;

    @Bind(R.id.viewSwitcher)
    ViewSwitcher mViewSwitcher;
    @Bind(R.id.signinLabel)
    TextView mSigninTextview;
    @Bind(R.id.signupLabel)
    TextView mSignupTextview;

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

        displayFragment(0);

        /*switchToTeacherBtn.setOnClickListener(v -> {
            Intent mIntent = new Intent(StudentLoginActivity.this, TeacherLoginActivity.class);
            startActivity(mIntent);
        });*/

        /*usernameText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                testvalide();
                return true;
            }
            return false;
        });

        passwordText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                testvalide();
                return true;
            }
            return false;
        });*/

        /*signinButton.setOnClickListener(v -> testvalide());

        signupButton.setOnClickListener(v -> {
            Intent mIntent = new Intent(StudentLoginActivity.this, StudentSignupActivity.class);
            startActivity(mIntent);
        });*/
    }

    private void testvalide() {
        /*String Usernam = usernameText.getText().toString();
        String Password = passwordText.getText().toString();

        if (TextUtils.isEmpty(Password)) {
            passwordText.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(Usernam)) {
            usernameText.setError(getString(R.string.erreur));
        }*/
    }

    private void displayFragment(int position) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (position) {
            case 0:
                fragment = new StudentFrag1();
                break;
        }

        ft.replace(R.id.fragment_container, fragment, "fragment" + position);
        ft.commit();
    }

    public void processHover() {

    }
}
