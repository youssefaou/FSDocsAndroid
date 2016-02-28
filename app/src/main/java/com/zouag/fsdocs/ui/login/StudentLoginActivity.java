package com.zouag.fsdocs.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.ui.signup.StudentSignupActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudentLoginActivity extends AppCompatActivity {

    @Bind(R.id.usernameText)
    EditText usernameText;
    @Bind(R.id.passwordText)
    EditText passwordText;
    @Bind(R.id.signinButtonE)
    Button signinButton;
    @Bind(R.id.switchToTeacherBtn)
    Button switchToTeacherBtn;
    @Bind(R.id.signupButtonE)
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        ButterKnife.bind(this); // Butterknife's setup

        switchToTeacherBtn.setOnClickListener(v -> {
            Intent mIntent = new Intent(StudentLoginActivity.this, TeacherLoginActivity.class);
            startActivity(mIntent);
        });

        usernameText.setOnEditorActionListener((v, actionId, event) -> {
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
        });

        signinButton.setOnClickListener(v -> testvalide());

        signupButton.setOnClickListener(v -> {
            Intent mIntent = new Intent(StudentLoginActivity.this, StudentSignupActivity.class);
            startActivity(mIntent);
        });
    }

    private void testvalide() {
        String Usernam = usernameText.getText().toString();
        String Password = passwordText.getText().toString();

        if (TextUtils.isEmpty(Password)) {
            passwordText.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(Usernam)) {
            usernameText.setError(getString(R.string.erreur));
        }
    }

}
