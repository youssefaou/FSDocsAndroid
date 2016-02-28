package com.zouag.fsdocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private Button btnEtudiant;
    private Button signupP;
    private Button signinP;
    private EditText userNameP;
    private EditText passwordP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnEtudiant = (Button)findViewById(R.id.buttonEtudiant);
        signupP = (Button)findViewById(R.id.sigupBtnP);
        userNameP =(EditText)findViewById(R.id.userNameProf);
        passwordP =(EditText)findViewById(R.id.paasswordProf);
        signinP = (Button)findViewById(R.id.signingBtn2);
        btnEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(mIntent);
            }

        });

        signupP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntente = new Intent(Main2Activity.this, signupprofesseur.class);
                startActivity(mIntente);
            }

        });
        signinP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testvalide();
            }
        });

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
