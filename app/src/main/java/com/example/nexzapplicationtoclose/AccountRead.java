package com.example.nexzapplicationtoclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class AccountRead extends AppCompatActivity {
    Button btnEdit;
    TextView txtViewName, txtViewUserName, txtViewMobile, txtViewEmail;
    /*private DBAccountHandler dbAccountHandler;
    SQLiteDatabase db;*/
    AccountHolderData accountHolderData = new AccountHolderData();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_read);

        //dbAccountHandler = new DBAccountHandler(AccountRead.this);

        getSupportActionBar().setTitle("Account Balance");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF018786"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar_layout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        btnEdit = findViewById(R.id.btnEdit);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.report:
                        startActivity(new Intent(getApplicationContext(), ReportView.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Intent i = getIntent();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij = new Intent(getApplicationContext(), AccountEdit.class);
                startActivity(ij);
            }
        });

        txtViewName = findViewById(R.id.txtViewNameAccount);
        txtViewUserName = findViewById(R.id.txtViewUserNameAccount);
        txtViewMobile = findViewById(R.id.txtViewMobileAccount);
        txtViewEmail = findViewById(R.id.txtViewEmailAccount);

        accountHolderData.setName(i.getStringExtra("name_Str"));
        accountHolderData.setUserName(i.getStringExtra("userName_Str"));
        accountHolderData.setMobile(i.getStringExtra("mobile_Str"));
        accountHolderData.setEmail(i.getStringExtra("email_Str"));
        /*ArrayList a = new ArrayList();
        a = dbAccountHandler.readAccountData();*/
        txtViewName.setText(accountHolderData.getName());
        txtViewUserName.setText(accountHolderData.getUserName());
        txtViewMobile.setText(accountHolderData.getMobile());
        txtViewEmail.setText(accountHolderData.getEmail());
    }
}