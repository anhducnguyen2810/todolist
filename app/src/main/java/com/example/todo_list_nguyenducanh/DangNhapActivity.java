package com.example.todo_list_nguyenducanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {

    EditText username , password;
    Button btnlogin;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        username = (EditText) findViewById(R.id.usrenamelogin);
        password = (EditText) findViewById(R.id.passwordlogin);
        btnlogin = (Button) findViewById(R.id.btnsinguplogin);
        database = new Database(this, "login.db", null, 1);

        //set su kien cho btnlogin
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("")||pass.equals(""))
                    Toast.makeText(DangNhapActivity.this, "Điền hết vào chỗ trống đã nhé",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = database.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(DangNhapActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
}