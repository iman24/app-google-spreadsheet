package com.imanancin.spreadsheetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class GrupActivity extends AppCompatActivity {

    AutoCompleteTextView grup, shift;
    AutoCompleteTextView editTextFilledExposedDropdown, editTextFilledExposedDropdown2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grup);

        grup = (AutoCompleteTextView) findViewById(R.id.grupp);
        shift = (AutoCompleteTextView) findViewById(R.id.shiftt);

        String[] type1 = new String[] {"Grup 1","Grup 2","Grup 3","Grup SPV"};
        String[] type2 = new String[] {"Shift 1","Shift 2","Shift 3","Shift 4"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        type1);

        editTextFilledExposedDropdown = findViewById(R.id.grupp);
        editTextFilledExposedDropdown.setAdapter(adapter);

        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        type2);

        editTextFilledExposedDropdown2 = findViewById(R.id.shiftt);
        editTextFilledExposedDropdown2.setAdapter(adapter2);



    }

    public void toBarcode(View view) {

        if (editTextFilledExposedDropdown.getText().toString().equals("") || editTextFilledExposedDropdown2.getText().toString().equals("")) {
            Snackbar.make(view, "Data Belum dipilih", Snackbar.LENGTH_SHORT).show();
        } else {
//            Snackbar.make(view, editTextFilledExposedDropdown.getText(), Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra("grup",editTextFilledExposedDropdown.getText().toString());
            intent.putExtra("shift",editTextFilledExposedDropdown2.getText().toString());
            startActivity(intent);
        }

    }
}