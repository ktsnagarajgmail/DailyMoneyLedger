package com.example.nexzapplicationtoclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
public class AccountRead extends AppCompatActivity {
    Button btnEdit;
    TextView txtViewName, txtViewUserName, txtViewMobile, txtViewEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_read);

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
        btnEdit = findViewById(R.id.btnEdit);
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

        txtViewName.setText(i.getStringExtra("name_Str"));
        txtViewUserName.setText(i.getStringExtra("userName_Str"));
        txtViewMobile.setText(i.getStringExtra("mobile_Str"));
        txtViewEmail.setText(i.getStringExtra("email_Str"));
    }
}