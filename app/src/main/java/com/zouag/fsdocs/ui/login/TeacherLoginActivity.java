package com.zouag.fsdocs.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.ui.signup.TeacherSignupActivity;

public class TeacherLoginActivity extends AppCompatActivity {
    private Button btnEtudiant;
    private Button signupP;
    private Button signinP;
    private EditText userNameP;
    private EditText passwordP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        btnEtudiant = (Button)findViewById(R.id.buttonEtudiant);
        signupP = (Button)findViewById(R.id.sigupBtnP);
        userNameP =(EditText)findViewById(R.id.userNameProf);
        passwordP =(EditText)findViewById(R.id.paasswordProf);
        signinP = (Button)findViewById(R.id.signingBtn2);
        btnEtudiant.setOnClickListener(v -> {
            Intent mIntent = new Intent(TeacherLoginActivity.this, StudentLoginActivity.class);
            startActivity(mIntent);
        });

        signupP.setOnClickListener(v -> {
            Intent mIntente = new Intent(TeacherLoginActivity.this, TeacherSignupActivity.class);
            startActivity(mIntente);
        });
        signinP.setOnClickListener(v -> testvalide());

/*

        */
    }
    private void testvalide() {
        String Usernam = userNameP.getText().toString();
        String Password = passwordP.getText().toString();

        if (TextUtils.isEmpty(Password)) {
            passwordP.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(Usernam)) {
            userNameP.setError(getString(R.string.erreur));
            return;
        }
    }
}
