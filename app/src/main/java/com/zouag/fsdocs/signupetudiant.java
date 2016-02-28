package com.zouag.fsdocs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signupetudiant extends AppCompatActivity {
    private EditText TextEmail;
    private EditText TextPassword;
    private EditText TextConfirmPassword;
    private Button newAcount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupetudiant);
        TextEmail = (EditText)findViewById(R.id.textEmailEtud_new);
        TextPassword =(EditText)findViewById(R.id.paasswordET_new);
        TextConfirmPassword = (EditText)findViewById(R.id.confirmpaasswordET_new);
        newAcount =(Button)findViewById(R.id.sigupBtn_new);
        newAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testvalide();
            }
        });
    }

    private void testvalide() {
        String EmailE = TextEmail.getText().toString();
        String PasswordE = TextPassword.getText().toString();
        String confPasswordE = TextConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(EmailE)) {
            TextEmail.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(PasswordE)) {
            TextPassword.setError(getString(R.string.erreur));
            return;
        }
        if (TextUtils.isEmpty(confPasswordE)) {
            TextConfirmPassword.setError(getString(R.string.erreur));
            return;
        }
        if(!PasswordE.equals(confPasswordE))
        {
            TextConfirmPassword.setError(getString(R.string.conf_pas));
            return;
        }
    }
}
