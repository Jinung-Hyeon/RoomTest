package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText up_edt_name, up_edt_age;
    Button btn_update;

    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        up_edt_name = findViewById(R.id.up_edt_name);
        up_edt_age = findViewById(R.id.up_edt_age);
        btn_update = findViewById(R.id.btn_update);

        // 아이템 어뎁터에서 넘어온 데이터 변수에 담기
        String name = getIntent().getStringExtra("userName");
        String age = getIntent().getStringExtra("userAge");
        uid = getIntent().getIntExtra("uid", 0);

        // 변수 화면에 보여주기
        up_edt_name.setText(name);
        up_edt_age.setText(age);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 입력값 변수에 담기
                String userName = up_edt_name.getText().toString();
                String userAge = up_edt_age.getText().toString();

                // 사용자 클래스 생성
                User user = new User();
                user.uid = uid;
                user.userName = userName;
                user.userAge = userAge;

                // DB 생성
                AppDatabase db = AppDatabase.getDBInstance(UpdateActivity.this);

                // 데이터 수정
                db.userDao().userUpdate(user);

                // 메인 화면으로 이동
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);

                // UpdateActivity 종료
                finish();
            }
        });
    }
}