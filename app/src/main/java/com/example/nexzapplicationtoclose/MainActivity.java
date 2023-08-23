package com.example.nexzapplicationtoclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    private RadioGroup rdG;
    private RadioButton rdB;
    private EditText amount, desc;
    private Button add, reportView;
    private DBHandler dbHandler;
    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reportView = findViewById(R.id.buttonReport);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), AccountRead.class));
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

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View viewable = layoutInflater.inflate(R.layout.activity_new_items, null);
                PopupWindow popupWindow = new PopupWindow(viewable, 1000, 730, true);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                rdG = viewable.findViewById(R.id.rdG);
                add = viewable.findViewById(R.id.btnAdd);
                amount = viewable.findViewById(R.id.editAmount);
                desc = viewable.findViewById(R.id.editDesc);
                rdG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rdB = radioGroup.findViewById(i);
                    }
                });

                dbHandler = new DBHandler(MainActivity.this);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String type = rdB.getText().toString();
                        String amt = amount.getText().toString();
                        String descrip = desc.getText().toString();
                        if (type.isEmpty() && amt.isEmpty() && descrip.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        dbHandler.addNewCourse(type, amt, descrip);
                        Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                        rdG.clearCheck();
                        amount.setText("");
                        desc.setText("");
                        popupWindow.dismiss();

                    }
                });

            }
        });

        reportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ReportView.class);
                startActivity(i);
            }
        });
    }
}