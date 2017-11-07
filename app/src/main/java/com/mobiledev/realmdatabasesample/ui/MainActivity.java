package com.mobiledev.realmdatabasesample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mobiledev.realmdatabasesample.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addButton;
    private Button showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.addButton);
        showButton = findViewById(R.id.showButton);

        addButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addButton){
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.putExtra("ADD", true);
            startActivity(intent);
        }
        else if (v.getId() == R.id.showButton){
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        }
    }
}
