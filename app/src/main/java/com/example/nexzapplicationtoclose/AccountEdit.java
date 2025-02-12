package com.example.nexzapplicationtoclose;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountEdit extends AppCompatActivity {

    Button btnSave, btnBack;
    EditText name, userName, mobile, email;
    String nameStr, userNameStr, mobileStr, emailStr;
    /*private DBAccountHandler dbAccountHandler;
    SQLiteDatabase db;*/
    AccountHolderData accountHolderData = new AccountHolderData();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        //dbAccountHandler = new DBAccountHandler(AccountEdit.this);

        getSupportActionBar().setTitle("Account Balance");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF018786"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar_layout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    name = findViewById(R.id.editTextName);
    userName = findViewById(R.id.editTextUserName);
    mobile = findViewById(R.id.editTextMobile);
    email = findViewById(R.id.editTextEmail);
    btnSave = findViewById(R.id.btnSave);
    btnBack = findViewById(R.id.btnBack);

    btnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nameStr = name.getText().toString();
            userNameStr = userName.getText().toString();
            mobileStr = mobile.getText().toString();
            emailStr = email.getText().toString();
            //dbAccountHandler.addAccountData(nameStr, userNameStr, mobileStr, emailStr);
            accountHolderData.setName(nameStr);
            accountHolderData.setUserName(userNameStr);
            accountHolderData.setMobile(mobileStr);
            accountHolderData.setEmail(emailStr);
            Intent i = new Intent(getApplicationContext(), AccountRead.class);
            i.putExtra("name_Str", accountHolderData.getName());
            i.putExtra("userName_Str", accountHolderData.getUserName());
            i.putExtra("mobile_Str", accountHolderData.getMobile());
            i.putExtra("email_Str", accountHolderData.email);
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