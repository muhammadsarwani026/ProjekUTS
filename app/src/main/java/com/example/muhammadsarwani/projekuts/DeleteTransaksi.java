package com.example.muhammadsarwani.projekuts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DeleteTransaksi extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    public RadioGroup radioGroup;
    public RadioButton radio;
    public Button buttonDelete, buttonKembali;
    public EditText editTextTanggal, editTextNominal, editTextUraian, editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_transaksi);
        dbHelper        = new DatabaseHelper(this);
        editTextTanggal = (EditText)findViewById(R.id.editTextTanggal);
        editTextNominal = (EditText)findViewById(R.id.editTextNominal);
        editTextUraian  = (EditText)findViewById(R.id.editTextUraian);
        editTextID      = (EditText)findViewById(R.id.editTextID);

        radioGroup      = (RadioGroup)findViewById(R.id.radioGroup1);

        buttonDelete    = (Button)findViewById(R.id.buttonDelete);
        buttonKembali   = (Button)findViewById(R.id.buttonKembali);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long resultDelete = dbHelper.delete(Integer.parseInt(editTextID.getText().toString()));
                if (resultDelete > 0){
                    Intent in = new Intent(DeleteTransaksi.this, MainActivity.class);
                    startActivity(in);
                    Toast.makeText(DeleteTransaksi.this,"Data berhasil dihapus",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(DeleteTransaksi.this,"Data tidak berhasil dihapus",Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DeleteTransaksi.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
