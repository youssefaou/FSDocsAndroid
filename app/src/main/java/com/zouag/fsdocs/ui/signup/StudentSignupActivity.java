package com.zouag.fsdocs.ui.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudentSignupActivity extends AppCompatActivity {

    @Bind(R.id.studentEmailText)
    EditText emailText;
    @Bind(R.id.studentPasswordText)
    EditText passwordText;
    @Bind(R.id.studentConfirmPText)
    EditText confirmPasswordText;
    @Bind(R.id.studentSignupBtn)
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        ButterKnife.bind(this);
        signupButton.setOnClickListener(v -> testvalide());
    }

    private void testvalide() {
        String EmailE = emailText.getText().toString();
        String PasswordE = passwordText.getText().toString();
        String confPasswordE = confirmPasswordText.getText().toString();

        if (TextUtils.isEmpty(EmailE)) {
            emailText.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(PasswordE)) {
            passwordText.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(confPasswordE)) {
            confirmPasswordText.setError(getString(R.string.erreur));
            return;
        }
        if(!PasswordE.equals(confPasswordE))
            confirmPasswordText.setError(getString(R.string.conf_pas));
    }
}
