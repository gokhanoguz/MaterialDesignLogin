package com.javatar.materialdesignlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = LoginActivity.class.getSimpleName();


    private Button mLoginButton;
    private EditText mEmailNameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUIElements();

    }

    private void initUIElements() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mEmailNameEditText = (EditText) findViewById(R.id.email_edittext);
        mPasswordEditText = (EditText) findViewById(R.id.password_edittext);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfomLogin();
            }
        });
    }

    private void perfomLogin() {
        if (!checkEmailAndPassword()) {
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();

        String email = mEmailNameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        //Simulate network process for 3 seconds
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                        startMainActivity();
                    }
                }, 3000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean checkEmailAndPassword() {

        String email = mEmailNameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailNameEditText.setError(getResources().getString(R.string.enter_valid_email_error));
            return false;
        }

        // Change the rules according to yours
        if (password.isEmpty() || password.length() < 4 || password.length() > 12) {
            mPasswordEditText.setError(getResources().getString(R.string.enter_valid_password_error));
            return false;
        }
        return true;
    }

}
