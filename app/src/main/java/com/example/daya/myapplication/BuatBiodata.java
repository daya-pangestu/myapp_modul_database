package com.example.daya.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into biodata(no,nama,tgl,jk,alamat) values(" +text1.getText().toString() + "," + text2.getText().toString() + "," +text3.getText().toString() + "," +text4.getText().toString() + "," +
                        text5.getText().toString() + ")");

                Toast.makeText(getApplicationContext(), "BERHASIL", Toast.LENGTH_LONG).show();

                MainActivity.ma.RefreshList();
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
