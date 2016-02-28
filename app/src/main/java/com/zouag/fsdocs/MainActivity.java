package com.zouag.fsdocs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText UserName;
    private EditText password;
    private Button btnsign;
    private Button btnProf;
    private Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProf = (Button)findViewById(R.id.button);
        UserName = (EditText) findViewById(R.id.userNameEditText);
        password = (EditText) findViewById(R.id.paasswordET);
        btnsign = (Button) findViewById(R.id.signingBtn);
        btnsignup = (Button) findViewById(R.id.sigupBtn);
        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(mIntent);
            }

        });

        UserName.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ) {
                    testvalide();
                    return true;
                }
                return false;
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    testvalide();
                    return true;
                }
                return false;
            }
        });

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testvalide();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this,signupetudiant.class);
                startActivity(mIntent);
            }

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
