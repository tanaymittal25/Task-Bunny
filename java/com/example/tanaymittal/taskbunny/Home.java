package com.example.tanaymittal.taskbunny;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class Home extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Task Bunny");


        fabButton = findViewById(R.id.button_fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myDialog = new AlertDialog.Builder(Home.this);
                LayoutInflater inflater = LayoutInflater.from(Home.this);
                View myView = inflater.inflate(R.layout.custominputfield, null);
                myDialog.setView(myView);
                AlertDialog dialog = myDialog.create();

                final EditText title = myView.findViewById(R.id.text_title);
                final EditText note = myView.findViewById(R.id.text_note);

                Button save = myView.findViewById(R.id.btn_save);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String mTitle = title.getText().toString().trim();
                        String mNote = note.getText().toString().trim();

                        if(TextUtils.isEmpty(mTitle)) {
                            title.setError("Required Field..");
                            return;
                        }

                        if(TextUtils.isEmpty(mNote)) {
                            note.setError("Required Field..");
                            return;
                        }
                    }
                });

                dialog.show();

            }
        });

    }
}
