package com.zouag.fsdocs.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.ui.signup.StudentSignupActivity;

public class StudentLoginActivity extends Activity {

    private EditText UserName;
    private EditText password;
    private Button btnsign;
    private Button btnProf;
    private Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        btnProf = (Button)findViewById(R.id.button);
        UserName = (EditText) findViewById(R.id.userNameEditText);
        password = (EditText) findViewById(R.id.paasswordET);
        btnsign = (Button) findViewById(R.id.signingBtn);
        btnsignup = (Button) findViewById(R.id.sigupBtn);
        btnProf.setOnClickListener(v -> {
            Intent mIntent = new Intent(StudentLoginActivity.this, TeacherLoginActivity.class);
            startActivity(mIntent);
        });

        UserName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ) {
                testvalide();
                return true;
            }
            return false;
        });

        password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                testvalide();
                return true;
            }
            return false;
        });

        btnsign.setOnClickListener(v -> testvalide());

        btnsignup.setOnClickListener(v -> {
            Intent mIntent = new Intent(StudentLoginActivity.this,StudentSignupActivity.class);
            startActivity(mIntent);
        });
    }

    private void testvalide() {
        String Usernam = UserName.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Password)) {
            password.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(Usernam)) {
            UserName.setError(getString(R.string.erreur));
            return;
        }
    }

}
