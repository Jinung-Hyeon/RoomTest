package com.example.roomtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    List<User> userList;

    Context context;

    public UserAdapter(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int mPosition = holder.getAdapterPosition();

        holder.tv_name.setText(userList.get(position).userName);
        holder.tv_age.setText(userList.get(position).userAge);

        // 수정 화면으로 이동
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("uid", userList.get(mPosition).uid);
                intent.putExtra("userName", userList.get(mPosition).userName);
                intent.putExtra("userAge", userList.get(mPosition).userAge);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.userList == null){
            return 0;
        } else {
            return this.userList.size();
        }
    }

    // 리스트 저장
    public void setUserList(List<User> userList){
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_age;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_age = itemView.findViewById(R.id.tv_age);
        }
    }
}
