package com.example.tanaymittal.taskbunny;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Launch extends AppCompatActivity {

    private TextView signupText;
    private EditText emailLogin;
    private EditText passwordLogin;
    private Button btnLogin;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() !=  null) {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        mDialog = new ProgressDialog(this);
        signupText = findViewById(R.id.signup_text);
        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.button_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = emailLogin.getText().toString().trim();
                String mPass = passwordLogin.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)) {
                    emailLogin.setError("Required Field..");
                    return;
                }

                if(TextUtils.isEmpty(mPass)) {
                    passwordLogin.setError("Required Field..");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();
                mAuth.signInWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            mDialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(),"Error..", Toast.LENGTH_LONG).show();
                            mDialog.dismiss();
                        }
                    }
                });
             }
        });


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
}
