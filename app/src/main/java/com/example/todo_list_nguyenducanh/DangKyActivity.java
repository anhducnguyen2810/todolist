package com.example.todo_list_nguyenducanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {


    EditText username, password, repassword;
    Button signup, signin;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);


        database = new Database(this, "ghichu.sqlite", null, 1);

        username = (EditText) findViewById(R.id.usrename);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsingup);
        signin = (Button) findViewById(R.id.btnsingin);
        //set su kien cho signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                //kiem tra co dong con trong khong
                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(DangKyActivity.this, "Điền vào chỗ trống nhé", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        boolean checkuser = database.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = database.insertData(user, pass);
                            if (insert==true){
                                Toast.makeText(DangKyActivity.this, "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DangNhapActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(DangKyActivity.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(DangKyActivity.this,"Người dùng này đã tồn tại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //set su kien cho nut signin
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
}