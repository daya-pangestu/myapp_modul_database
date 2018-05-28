package com.example.daya.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
String[]  daftar;
ListView ListView01;
Menu menu;
protected Cursor cursor;
DataHelper dbcenter;
public static MainActivity ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=(Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent inte = new Intent(MainActivity.this,BuatBiodata.class);
                startActivity(inte);

            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int cc=0; cc <cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,daftar)); /*ini ada yang beda*/
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() { //ini ada yang beda
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { /*ada yang beda*/
                final String selection = daftar[arg2];//.getItemPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Biodata","Update Biodata","Hapus Biodata"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(),LihatBiodata.class);
                                i.putExtra("nama",selection);
                                startActivities(new Intent[]{i});
                                break;

                            case 1:
                                Intent in = new Intent(getApplicationContext(),UpdateBiodata.class);
                                in.putExtra("nama",selection);
                                startActivities(new Intent[]{in});

                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama = "+selection+"");
                                RefreshList();
                                break;
                        }

                    }
                });
                    builder.create().show();
            }


        });
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetChanged();
    }

}
