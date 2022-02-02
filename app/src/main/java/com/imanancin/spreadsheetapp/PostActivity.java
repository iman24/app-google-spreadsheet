package com.imanancin.spreadsheetapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    TextView grup, shift, barcode;
    String g,s,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        grup = (TextView) findViewById(R.id.grup);
        shift = (TextView) findViewById(R.id.shift);
        barcode = (TextView) findViewById(R.id.barcode);


        sharedPref = getSharedPreferences("mantap", Context.MODE_PRIVATE);
        g = sharedPref.getString("grup","kosong");
        s = sharedPref.getString("shift","kosong");
        b = sharedPref.getString("barcode","kosong");

        grup.setText(g);
        shift.setText(s);
        barcode.setText(b);
    }




    public  void postData(View v) {
        post();
    }

    private void post(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://script.google.com/macros/s/AKfycbxRQtEz3RO0MVEpiFGFGuCzPpDNWv5SORsdIXzWbOA0Wtt6RB9DkjUSRXdzEKggV3NlHw/exec";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        AlertCuy alert = new AlertCuy();
                        alert.show(getSupportFragmentManager(), "Test");
                        notifyUser(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                notifyUser(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id", "1VZGdFh3eCUC7Vz8LLNh9UYtmo9Qjbu41UMCnYNI8xiQ");
                params.put("country", "Tes");
                params.put("name","name");
                return params;
            }

            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void notifyUser(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    public void scan(View view) {
        Intent intent = new Intent(this, BarcodeActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                String returnString = data.getStringExtra("barcode");

                // set text view with string
                TextView textView = (TextView) findViewById(R.id.barcode);
                textView.setText(returnString);
            }
        }
    }


    public static class AlertCuy extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Sukses...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Siap", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}