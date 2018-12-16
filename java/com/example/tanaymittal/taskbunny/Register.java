package com.example.tanaymittal.taskbunny;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    private EditText nameReg;
    private EditText emailReg;
    private EditText passReg;
    private EditText rpassReg;
    private Button btnReg;
    private TextView loginText;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailReg = findViewById(R.id.email_register);
        passReg = findViewById(R.id.password_register);
        rpassReg = findViewById(R.id.repassword_register);
        btnReg = findViewById(R.id.button_register);
        loginText = findViewById(R.id.login_text);
        nameReg = findViewById(R.id.name_register);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Launch.class));
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mName = nameReg.getText().toString();
                String mEmail = emailReg.getText().toString().trim();
                String mPass = passReg.getText().toString().trim();
                String mPassCheck = rpassReg.getText().toString().trim();

                if(TextUtils.isEmpty(mName)) {
                    nameReg.setError("Required Field..");
                    return;
                }

                if(TextUtils.isEmpty(mEmail)) {
                    emailReg.setError("Required Field..");
                    return;
                }

                if(TextUtils.isEmpty(mPass)) {
                    passReg.setError("Required Field..");
                    return;
                }

                if(TextUtils.isEmpty(mPassCheck)) {
                    rpassReg.setError("Required Field..");
                    return;
                }

                if(!mPass.equals(mPassCheck)) {
                    rpassReg.setError("Enter Same Password..");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Successfully Registered..", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            mDialog.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "Error..", Toast.LENGTH_LONG).show();
                            mDialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}
