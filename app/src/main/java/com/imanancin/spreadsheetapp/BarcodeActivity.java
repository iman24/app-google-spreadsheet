package com.imanancin.spreadsheetapp;

import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.Result;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class BarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "Barcode";
    private ZXingScannerView mScannerView;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
//        setContentView(R.layout.activity_barcode);
//        mScannerView.startCamera();
        sharedPref = getSharedPreferences("mantap", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }


    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        editor.putString("barcode", rawResult.getText());
        editor.apply();

        Intent intent = new Intent();
        intent.putExtra("barcode", rawResult.getText());
        setResult(RESULT_OK, intent);
        finish();

        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
    }
}