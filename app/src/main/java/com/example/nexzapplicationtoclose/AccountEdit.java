package com.example.nexzapplicationtoclose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountEdit extends AppCompatActivity {

    Button btnSave, btnBack;
    EditText name, userName, mobile, email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

    name = findViewById(R.id.editTextName);
    userName = findViewById(R.id.editTextUserName);
    mobile = findViewById(R.id.editTextMobile);
    email = findViewById(R.id.editTextEmail);
    btnSave = findViewById(R.id.btnSave);
    btnBack = findViewById(R.id.btnBack);

    btnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nameStr = name.getText().toString();
            String userNameStr = userName.getText().toString();
            String mobileStr = mobile.getText().toString();
            String emailStr = email.getText().toString();
            Intent i = new Intent(getApplicationContext(), AccountRead.class);
            i.putExtra("name_Str", nameStr);
            i.putExtra("userName_Str", userNameStr);
            i.putExtra("mobile_Str", mobileStr);
            i.putExtra("email_Str", emailStr);
            startActivity(i);
        }
    });
    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), AccountRead.class);
            startActivity(i);
        }
    });
    }
}