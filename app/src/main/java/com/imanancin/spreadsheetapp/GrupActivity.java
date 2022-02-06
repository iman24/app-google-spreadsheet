package com.imanancin.spreadsheetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GrupActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    RadioGroup grup, shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grup);

        grup = (RadioGroup) findViewById(R.id.grup);
        shift = (RadioGroup) findViewById(R.id.shift);

        sharedPref = getSharedPreferences("mantap", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("grup", "");
        editor.putString("shift", "");
        editor.putString("barcode", "belum scan");


    }

    public void toBarcode(View view) {
        int gr = grup.getCheckedRadioButtonId();
        int sh = shift.getCheckedRadioButtonId();

        if (gr == -1 || sh == -1) {
            Toast.makeText(this, "Isi data dulu", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton g = (RadioButton) findViewById(gr);
            RadioButton s = (RadioButton) findViewById(sh);
            editor.putString("grup", g.getText().toString());
            editor.putString("shift", s.getText().toString());
            editor.apply();
            startActivity(new Intent(this, PostActivity.class));
        }
    }
}