package com.example.muhammadsarwani.projekuts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateTransaksi extends AppCompatActivity {

    protected Cursor cursor;

    DatabaseHelper dbHelper;
    public RadioGroup radioGroup;
    public RadioButton radio;
    public Button buttonUpdate, buttonKembali;
    public EditText editTextTanggal, editTextNominal, editTextUraian, editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaksi);

        dbHelper        = new DatabaseHelper(this);
        editTextTanggal = (EditText)findViewById(R.id.);
        editTextNominal = (EditText)findViewById(R.id.editTextNominal);
        editTextUraian  = (EditText)findViewById(R.id.editTextUraian);
        editTextID = (EditText)findViewById(R.id.editTextID);

        radioGroup      = (RadioGroup)findViewById(R.id.radioGroup1);

        buttonUpdate    = (Button)findViewById(R.id.buttonUpdate);
        buttonKembali   = (Button)findViewById(R.id.buttonKembali);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM transaksi WHERE id = '" +
                getIntent().getStringExtra("id") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            editTextTanggal.setText(cursor.getString(1));
            editTextNominal.setText(cursor.getString(2));
            editTextUraian.setText(cursor.getString(3));
        }
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        // daftarkan even onClick pada btnSimpan
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                int id = radioGroup.getCheckedRadioButtonId();
                radio= (RadioButton)findViewById(id);
                boolean isUpdate = dbHelper.update(
                        Integer.parseInt(editTextID.getText().toString()),
                        editTextTanggal.getText().toString(),
                        radio.getText().toString(),
                        editTextNominal.getText().toString(),
                        editTextUraian.getText().toString());
                if (isUpdate){
                    Toast.makeText(UpdateTransaksi.this,"Data berhasil diperbarui",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(UpdateTransaksi.this,"Data tidak berhasil diperbarui",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }

        });
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}