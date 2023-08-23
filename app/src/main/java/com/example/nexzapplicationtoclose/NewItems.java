package com.example.nexzapplicationtoclose;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NewItems extends AppCompatActivity {
    private RadioGroup rdG;
    private RadioButton rdB;
    private EditText amount, desc;
    private TextView txtView;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_items);
        /*rdG = findViewById(R.id.rdG);
        add = findViewById(R.id.btnAdd);
        txtView = findViewById(R.id.txtViewNewEntry);
        amount = findViewById(R.id.editAmount);
        desc = findViewById(R.id.editDesc);
        Toast.makeText(getApplicationContext(), "HiHi", Toast.LENGTH_LONG).show();
        rdG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rdB = radioGroup.findViewById(i);
            }
        });
        txtView.setText(rdB.getText());
        //Toast.makeText(getApplicationContext(), amount.getText().toString(), Toast.LENGTH_LONG).show();*/
    }
}