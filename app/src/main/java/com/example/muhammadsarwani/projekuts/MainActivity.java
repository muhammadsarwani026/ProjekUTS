package com.example.muhammadsarwani.projekuts;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected Cursor cursor;

    Button buttonBuatTransaksi;
    DatabaseHelper dbcenter;
    TextView textViewSaldo;
    String []daftar;
    ListView ListView01;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBuatTransaksi = (Button) findViewById(R.id.buttonButtonTransaksi);
        textViewSaldo = (TextView)findViewById(R.id.textViewSaldo);

        buttonBuatTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahTransaksi.class);
                startActivity(intent);
            }
        });
        dbcenter = new DatabaseHelper(this);
        ma = this;
        dbcenter = new DatabaseHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        int total =0;
        final SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM transaksi",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = "ID \t\t\t\t\t\t:\t"+
                    cursor.getString(0) +
                    "\n"+
                    "Tanggal \t:\t"+
                    cursor.getString(1)+
                    "\n"+
                    cursor.getString(2)+
                    "\t\t\t\t:\tRp. "+
                    cursor.getString(3)+
                    "\n"+
                    "Uraian\t\t\t:\t"+
                    cursor.getString(4);

                    if( cursor.getString(2).equalsIgnoreCase("debit")){
                        total= total+Integer.parseInt(cursor.getString(3));
                    } else{
                        total= total-Integer.parseInt(cursor.getString(3));
                    }
            }
            textViewSaldo.setText(String.valueOf("Rp. "+total));

            ListView01 = (ListView)findViewById(R.id.listView1);
            ListView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftar));
            ListView01.setSelected(true);
            ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                    final String selection = daftar[arg2];
                    final CharSequence[] dialogitem = {"Update Transaksi", "Hapus Tramsaksi"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pilihan");
                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch(item){
                                case 0 :
                                    Intent in = new Intent(getApplicationContext(), UpdateTransaksi.class);
                                    in.putExtra("id", selection);
                                    startActivity(in);
                                    break;
                                case 1 :
                                    SQLiteDatabase db = dbcenter.getWritableDatabase();
                                    Intent in1 = new Intent(getApplicationContext(), DeleteTransaksi.class);
                                    in1.putExtra("id", selection);
                                    startActivity(in1);
                                    RefreshList();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }});
                ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();


            }
}

