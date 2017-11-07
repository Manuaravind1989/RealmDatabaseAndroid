package com.mobiledev.realmdatabasesample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobiledev.realmdatabasesample.R;
import com.mobiledev.realmdatabasesample.db.RealmHelper;
import com.mobiledev.realmdatabasesample.model.UserInfo;

/**
 * Created by Manu on 11/6/2017.
 */

public class UserListActivity extends AppCompatActivity {

    RealmHelper realmHelper;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        realmHelper = new RealmHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        for (UserInfo userInfo : realmHelper.queryAllUsers()) {
            Log.e("Title ======> ", userInfo.getName());
        }
        if (realmHelper.queryAllUsers().size() > 0) {
            UserInfoAdapter adapter = new UserInfoAdapter(this, realmHelper.queryAllUsers(), 0);
            recyclerView.setAdapter(adapter);
        }
    }
}
