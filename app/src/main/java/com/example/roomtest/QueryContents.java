package com.example.roomtest;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QueryContents{

    private Context mContext;
    UserAdapter adapter;

    public void loadUserList(Context context){
        this.mContext = context;
        AppDatabase db = AppDatabase.getDBInstance(mContext.getApplicationContext());
        List<User> userList = db.userDao().getAllUser();
        // UserAdapter 초기화
        adapter = new UserAdapter(context, userList);
        //adapter.setUserList(userList);
        for (User user : userList) {
            Log.e("msg", "userList : " + user.userName);
        }
        adapter.notifyDataSetChanged();
    }
}
