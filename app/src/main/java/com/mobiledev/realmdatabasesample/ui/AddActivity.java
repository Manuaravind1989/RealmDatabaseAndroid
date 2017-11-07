package com.mobiledev.realmdatabasesample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobiledev.realmdatabasesample.R;
import com.mobiledev.realmdatabasesample.db.RealmHelper;
import com.mobiledev.realmdatabasesample.model.UserInfo;

/**
 * Created by Manu on 11/6/2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameTV, ageTV, phoneTV, placeTV, emailTV;
    private Button submitButton, deleteButton;
    private RealmHelper mRealmHelper;
    private Boolean isAdd = false;
    private String nameStr, ageStr, phoneStr, placeStr, emailStr;
    private int idStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mRealmHelper = new RealmHelper(this);
        nameTV = findViewById(R.id.nameTV);
        ageTV = findViewById(R.id.ageTV);
        phoneTV = findViewById(R.id.phoneTV);
        placeTV = findViewById(R.id.placeTV);
        emailTV = findViewById(R.id.emailTV);
        submitButton = findViewById(R.id.submitButton);
        deleteButton = findViewById(R.id.deleteButton);
        submitButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        isAdd = getIntent().getBooleanExtra("ADD", true);
        idStr = getIntent().getIntExtra("ID", 0);

        UserInfo userInfo = mRealmHelper.queryUserInfoById(idStr);
        if (!isAdd && userInfo != null) {
            nameStr = userInfo.getName();
            ageStr = userInfo.getAge() + "";
            phoneStr = userInfo.getPhone() + "";
            placeStr = userInfo.getPlace();
            emailStr = userInfo.getEmail();

//        nameStr = getIntent().getStringExtra("NAME");
//        ageStr = getIntent().getStringExtra("AGE");
//        phoneStr = getIntent().getStringExtra("PHONE");
//        placeStr = getIntent().getStringExtra("PLACE");
//        emailStr = getIntent().getStringExtra("EMAIL");

            nameTV.setText(nameStr);
            ageTV.setText(ageStr);
            phoneTV.setText(phoneStr);
            placeTV.setText(placeStr);
            emailTV.setText(emailStr);
            deleteButton.setVisibility(View.VISIBLE);
        }


    }

    private boolean isValid() {
        if (nameTV.getText().toString().length() == 0) {
            Toast.makeText(AddActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (ageTV.getText().toString().length() == 0) {
            Toast.makeText(AddActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneTV.getText().toString().length() == 0) {
            Toast.makeText(AddActivity.this, "Enter Phone", Toast.LENGTH_SHORT).show();
            return false;
        } else if (placeTV.getText().toString().length() == 0) {
            Toast.makeText(AddActivity.this, "Enter Place", Toast.LENGTH_SHORT).show();
            return false;
        } else if (emailTV.getText().toString().length() == 0) {
            Toast.makeText(AddActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void clearAllFields() {
        nameTV.setText("");
        ageTV.setText("");
        phoneTV.setText("");
        placeTV.setText("");
        emailTV.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitButton) {
            if (isValid()) {
                if (isAdd) {
                    mRealmHelper.getRealm().beginTransaction();
                    UserInfo userInfo = mRealmHelper.getRealm().createObject(UserInfo.class);
                    userInfo.setId((int) (mRealmHelper.queryAllUsers().size() + System.currentTimeMillis()));
                    userInfo.setName(nameTV.getText().toString());
                    userInfo.setAge(Integer.valueOf(ageTV.getText().toString()));
                    userInfo.setPhone(Integer.valueOf(phoneTV.getText().toString()));
                    userInfo.setPlace(placeTV.getText().toString());
                    userInfo.setEmail(emailTV.getText().toString());
                    mRealmHelper.addUserInfo(userInfo);

                } else {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setName(nameTV.getText().toString());
                    userInfo.setAge(Integer.valueOf(ageTV.getText().toString()));
                    userInfo.setPhone(Integer.valueOf(phoneTV.getText().toString()));
                    userInfo.setPlace(placeTV.getText().toString());
                    userInfo.setEmail(emailTV.getText().toString());
                    mRealmHelper.updateUserInfo(idStr, userInfo);
                }
                clearAllFields();
            }
        } else if (v.getId() == R.id.deleteButton) {
            mRealmHelper.deleteUserInfo(idStr);
            finish();
        }
    }
}
