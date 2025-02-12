package com.example.nexzapplicationtoclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    TextView txtCreditValue, txtDebitValue, txtABValue;
    private RadioGroup rdG;
    private RadioButton rdB;
    private EditText amount, desc;
    private Button add, reportView;
    private DBHandler dbHandler;
    private ArrayList<AccountData> accountDataArrayList;
    private EntryRVAdapter entryRVAdapter;
    private RecyclerView entryRV;
    SQLiteDatabase db;
    double creditValue=0, debitValue=0, finalABValue = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecimalFormat decimalFormats = new DecimalFormat("#,###.00");
        getSupportActionBar().setTitle("Account Balance");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF018786"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar_layout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        dbHandler = new DBHandler(MainActivity.this);

        //reportView = findViewById(R.id.buttonReport);
        txtCreditValue = findViewById(R.id.txtCreditValue);
        txtDebitValue = findViewById(R.id.txtDebitValue);
        txtABValue = findViewById(R.id.txtABValue);

        txtCreditValue.setText(dbHandler.readCreditValue());
        txtDebitValue.setText(dbHandler.readDebitValue());
        txtABValue.setText(dbHandler.abValue());
        /*Toast.makeText(this, txtCreditValue.getText().toString(), Toast.LENGTH_LONG).show();
        creditValue = Double.parseDouble(txtCreditValue.getText().toString());
        Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
        debitValue = Double.parseDouble(txtDebitValue.getText().toString());
        Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
        finalABValue = creditValue - debitValue;
        Toast.makeText(this, "4", Toast.LENGTH_LONG).show();
        String finalABVal = decimalFormats.format(finalABValue);

      // txtABValue.setText(String.valueOf(dbHandler.readCreditValue()-dbHandler.readDebitValue()));
       //txtABValue.setText(String.valueOf(Double.parseDouble(dbHandler.readCreditValue())-Double.parseDouble(dbHandler.readDebitValue())));
        txtABValue.setText(finalABVal);*/

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
                        Toast.makeText(MainActivity.this, "Entry added!", Toast.LENGTH_SHORT).show();

                       // if (type.equals("Credit(+)")){
                            //creditValue = creditValue+Integer.parseInt(amt);
                            //txtCreditValue.setText(String.valueOf(dbHandler.readCreditValue()));
                        txtCreditValue.setText(dbHandler.readCreditValue());
                      //  if (type.equals("Debit(-)")){
                            //debitValue = debitValue+Integer.parseInt(amt);
                            //debitValue = dbHandler.readDebitValue();
                            txtDebitValue.setText(dbHandler.readDebitValue());
                        txtABValue.setText(dbHandler.abValue());
                        //txtABValue.setText(String.valueOf(dbHandler.readCreditValue()-dbHandler.readDebitValue()));
                       // txtABValue.setText(decimalFormats.format(Double.parseDouble(txtCreditValue.getText().toString())-Double.parseDouble(txtDebitValue.getText().toString())));
                        rdG.clearCheck();
                        amount.setText("");
                        desc.setText("");
                        popupWindow.dismiss();

                    }
                });
            }
        });

       /* reportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ReportView.class);
                startActivity(i);
            }
        });*/

        accountDataArrayList = dbHandler.readTopCourses();
        entryRVAdapter = new EntryRVAdapter(accountDataArrayList, MainActivity.this);
        entryRV = findViewById(R.id.idRVCoursesMain);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        entryRV.setLayoutManager(linearLayoutManager);

        entryRV.setAdapter(entryRVAdapter);
    }
}