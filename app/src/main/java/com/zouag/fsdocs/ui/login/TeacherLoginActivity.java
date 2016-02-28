package com.zouag.fsdocs.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.ui.signup.TeacherSignupActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeacherLoginActivity extends AppCompatActivity {

    @Bind(R.id.switchToStudentBtn)
    Button switchToStudentBtn;
    @Bind(R.id.signupButtonP)
    Button signupButton;
    @Bind(R.id.signinButtonP)
    Button signinButton;
    @Bind(R.id.userNameProf)
    EditText usernameText;
    @Bind(R.id.passwordProf)
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        ButterKnife.bind(this);

        switchToStudentBtn.setOnClickListener(v -> {
            Intent mIntent = new Intent(TeacherLoginActivity.this, StudentLoginActivity.class);
            startActivity(mIntent);
        });

        signupButton.setOnClickListener(v -> {
            Intent mIntente = new Intent(TeacherLoginActivity.this, TeacherSignupActivity.class);
            startActivity(mIntente);
        });
        signinButton.setOnClickListener(v -> testvalide());
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
