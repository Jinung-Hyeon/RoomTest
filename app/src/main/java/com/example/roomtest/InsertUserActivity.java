package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertUserActivity extends AppCompatActivity {
    EditText edt_name, edt_age;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user_acitivity);

        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                String age = edt_age.getText().toString();

                // 사용자 등록
                userInsert(name, age);
            }
        });
    }

    /*
    * 사용자 등록
    * @param name 이름
    * @param age 나이
    */

    private void userInsert(String name, String age){
        User user = new User();
        user.userName = name;
        user.userAge = age;

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        db.userDao().insertUser(user);

        setResult(Activity.RESULT_OK);

        finish();
    }
}