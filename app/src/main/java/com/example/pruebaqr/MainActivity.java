package com.example.pruebaqr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button btnScanner;
    private TextView tvBarCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScanner=findViewById(R.id.btnScanner);
        tvBarCode=findViewById(R.id.tvBarCode);

        btnScanner.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null)
            if(result.getContents() != null)
            {
                tvBarCode.setText("El codigo es; \n" + result.getContents());
            }else{
                tvBarCode.setText("error al scanear");
            }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btnScanner:
                    new IntentIntegrator(MainActivity.this).initiateScan();
                    break;
            }
        }
    };
}
