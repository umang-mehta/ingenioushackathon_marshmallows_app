package com.example.me.inghack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LicenseInfoActivity extends AppCompatActivity {

    TextView licenseNo;
    String license = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent get_selected = getIntent();
        license = (String) get_selected.getExtras().get("LicenseInfo");
        Toast.makeText(this, "Dl no :" + license, Toast.LENGTH_SHORT).show();
        licenseNo = (TextView) findViewById(R.id.licenseNO);
        licenseNo.setText("" + license);
    }
}
