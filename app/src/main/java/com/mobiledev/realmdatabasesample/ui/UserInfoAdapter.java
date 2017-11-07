package com.mobiledev.realmdatabasesample.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiledev.realmdatabasesample.R;
import com.mobiledev.realmdatabasesample.model.UserInfo;

import java.util.List;

/**
 * Created by Manu on 11/6/2017.
 */

public class UserInfoAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserInfo> mValues;
    private Context context;
    private int type;

    public UserInfoAdapter(Context context, List<UserInfo> items, int type) {
        this.context = context;
        mValues = items;
        this.type = type;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        holder.nameTV.setText(mValues.get(position).getName());
        holder.ageTV.setText( mValues.get(position).getAge() + "");
        holder.phoneTV.setText( mValues.get(position).getPhone()+"");

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddActivity.class);
                intent.putExtra("ADD", false);
                intent.putExtra("ID",mValues.get(position).getId());
//                intent.putExtra("NAME",mValues.get(position).getName());
//                intent.putExtra("AGE",mValues.get(position).getAge()+"");
//                intent.putExtra("PHONE",mValues.get(position).getPhone()+"");
//                intent.putExtra("PLACE",mValues.get(position).getPlace());
//                intent.putExtra("EMAIL",mValues.get(position).getEmail());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
