package com.example.daya.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;



public class BuatBiodata extends AppCompatActivity {
    protected android.database.Cursor cursor;
    DataHelper dbHelper;
    Button btn1,btn2;
    EditText text1,text2,text3,text4,text5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata);

        dbHelper = new DataHelper(this);

        text1 = (EditText)findViewById(R.id.editText1);
        text2 = (EditText)findViewById(R.id.editText2);
        text3 = (EditText)findViewById(R.id.editText3);
        text4 = (EditText)findViewById(R.id.editText4);
        text5 = (EditText)findViewById(R.id.editText5);
        btn1  = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);

    }
}
