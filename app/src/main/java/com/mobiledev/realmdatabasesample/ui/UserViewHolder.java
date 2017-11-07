package com.mobiledev.realmdatabasesample.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiledev.realmdatabasesample.R;

/**
 * Created by Manu on 11/6/2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {
    public final TextView nameTV;
    public final TextView ageTV;
    public final TextView phoneTV;
    public final LinearLayout itemLayout;


    public UserViewHolder(View view) {
        super(view);
        nameTV =  view.findViewById(R.id.nameTV);
        ageTV =  view.findViewById(R.id.ageTV);
        phoneTV =  view.findViewById(R.id.phoneTV);
        itemLayout =  view.findViewById(R.id.ItemLayout);

    }
}