package com.example.me.inghack.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.me.inghack.R;

public class AuthenticationActivity extends AppCompatActivity {

    EditText ed_dlno;
    Button checkDl;
    String dlnoText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setAuthId();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        checkDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlnoText = ed_dlno.getText().toString().trim();

                //Toast.makeText(AuthenticationActivity.this, "dlnoText :" + dlnoText +" : "+ dlnoText.isEmpty()+ dlnoText.length() , Toast.LENGTH_SHORT).show();


                if (!dlnoText.isEmpty() && dlnoText.length() == 15 && dlnoText != null) {
                    //EditText is empty


                    Intent moveToLicense = new Intent(AuthenticationActivity.this, LicenseInfoActivity.class);
                    moveToLicense.putExtra("LicenseInfo", dlnoText);
                    startActivity(moveToLicense);
                    finish();


                    Toast.makeText(AuthenticationActivity.this, "dlnoText :" + dlnoText + " : " + dlnoText.isEmpty() + dlnoText.length(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AuthenticationActivity.this, "Enter Valid License Number.", Toast.LENGTH_SHORT).show();
                    //EditText is not empty
                }

            }
        });

    }

    private void setAuthId() {

        checkDl = (Button) findViewById(R.id.dlCheck);
        ed_dlno = (EditText) findViewById(R.id.dlno);

    }
}
