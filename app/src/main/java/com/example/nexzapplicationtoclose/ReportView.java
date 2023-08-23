package com.example.nexzapplicationtoclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ReportView extends AppCompatActivity {
    TextView txtview;
    Button btnDashboard;
    SQLiteDatabase db;

    private ArrayList<AccountData> accountDataArrayList;
    private DBHandler dbHandler;
    private EntryRVAdapter entryRVAdapter;
    private RecyclerView entryRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);

        accountDataArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ReportView.this);
        //txtview = findViewById(R.id.textView2);
        //db = dbHandler.getWritableDatabase();
       /* db = dbHandler.getReadableDatabase();
        btnDashboard = findViewById(R.id.button);*/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.report);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.report:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), AccountRead.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        /*btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor c = db.rawQuery("SELECT * FROM dailyLedger", null);
                while (c.moveToNext()) {
                    buffer.append("\n"+c.getString(0));
                    buffer.append("\n"+c.getString(1));
                    buffer.append("\n"+c.getString(2));
                    buffer.append("\n"+c.getString(3));
                }
                txtview.setText(buffer.toString());
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
            }
        });*/
        accountDataArrayList = dbHandler.readCourses();
        entryRVAdapter = new EntryRVAdapter(accountDataArrayList, ReportView.this);
        entryRV = findViewById(R.id.idRVCourses);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReportView.this, RecyclerView.VERTICAL, false);
        entryRV.setLayoutManager(linearLayoutManager);

        entryRV.setAdapter(entryRVAdapter);
    }
}