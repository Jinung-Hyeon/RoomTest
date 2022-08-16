package com.example.roomtest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    UserAdapter adapter;
    QueryContents qc;
    RecyclerView recyclerView;

    // launchMode 를 singleTask(중복 생성방지) 로했기때문에 onCreate를 타지않고 onNewIntent를 탐
    // 액티비티가 백그라운드에 있는데 호출되면 실행됨
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


        Log.e("msg", "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("msg", "onResume");
        recyclerView.setAdapter(qc.adapter);
        qc.loadUserList(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qc = new QueryContents();


        fab = findViewById(R.id.btn_insert);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertUserActivity.class);
                activityResult.launch(intent);
            }
        });

        // 사용자 조회
        qc.loadUserList(this);

        // RecyclerView 초기화 및 생성
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        // 사용자 조회
                        qc.loadUserList(MainActivity.this);
                    }
                }
            }
    );

//    // 사용자 조회
//    private void loadUserList() {
//        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
//
//        List<User> userList = db.userDao().getAllUser();
//
//        // 리스트 저장
//        adapter.setUserList(userList);
//        for (User user : userList) {
//            Log.e("msg", "userList : " + user.userName);
//        }
//    }
}

