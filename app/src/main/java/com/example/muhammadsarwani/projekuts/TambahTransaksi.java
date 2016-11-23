package com.example.muhammadsarwani.projekuts;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TambahTransaksi extends AppCompatActivity {

    public RadioGroup radioGroup;
    public RadioButton radio;
    public Button buttonTambah,buttonKembali   ;
    public EditText editTextTanggal, editTextNominal, editTextUraian, editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi);
        final DatabaseHelper myDB = new DatabaseHelper(this);
        editTextTanggal = (EditText)findViewById(R.id.editTextTanggal);
        editTextNominal = (EditText)findViewById(R.id.editTextNominal);
        editTextUraian  = (EditText)findViewById(R.id.editTextUraian);
        editTextID      = (EditText)findViewById(R.id.editTextID);

        radioGroup      = (RadioGroup)findViewById(R.id.radioGroup1);

        buttonTambah    = (Button)findViewById(R.id.buttonTambah);
        buttonKembali   = (Button)findViewById(R.id.buttonKembali);


        buttonTambah.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             int id = radioGroup.getCheckedRadioButtonId();
             radio  = (RadioButton)findViewById(id);

             boolean berhasil = myDB.insert(
                     Integer.parseInt(editTextID.getText().toString()),
                     editTextTanggal.getText().toString(),
                     radio.getText().toString(),
                     editTextNominal.getText().toString(),
                     editTextUraian.getText().toString());

             if (berhasil){
                 Intent intent = new Intent(TambahTransaksi.this, MainActivity.class);
                 startActivity(intent);
             }
             else{
                Toast.makeText(TambahTransaksi.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
             }
         }
     });
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(TambahTransaksi.this, MainActivity.class);
                startActivity(in);
            }
        });
    }

}