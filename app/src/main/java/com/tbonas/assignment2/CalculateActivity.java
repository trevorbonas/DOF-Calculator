package com.tbonas.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tbonas.assignment2.model.Lens;

public class CalculateActivity extends AppCompatActivity {

    private Lens picked_lens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
    }
}