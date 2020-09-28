package com.tbonas.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tbonas.assignment2.model.Lens;

public class CalculateActivity extends AppCompatActivity {
    private static final String MAKE_EXTRA =
            "com.tbonas.assignment2.CalculateActivity - Lens make";
    private static final String FOCAL_EXTRA =
            "com.tbonas.assignment2.CalculateActivity - Lens focal length";
    private static final String APERTURE_EXTRA =
            "com.tbonas.assignment2.CalculateActivity - Lens max aperture";

    private String make;
    private int focal;
    private double max_aperture;


    private Lens picked_lens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        extractDataFromIntent();
        updateMakeLabel();
    }

    private void updateMakeLabel() {
        TextView label = (TextView)findViewById(R.id.details_for_make_label);
        String label_default = label.getText().toString();
        label_default = label_default + make + " " + focal + "mm " +
                "F" + max_aperture;
        label.setText(label_default);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        make = intent.getStringExtra(MAKE_EXTRA);
        focal = intent.getIntExtra(FOCAL_EXTRA, 0);
        max_aperture = intent.getDoubleExtra(APERTURE_EXTRA, 0.00);
    }

    public static Intent makeIntent(Context context, Lens lens) {
        Intent intent = new Intent(context, CalculateActivity.class);
        intent.putExtra(MAKE_EXTRA,
                lens.getMake());
        intent.putExtra(FOCAL_EXTRA,
               lens.getFocal_length());
        intent.putExtra(APERTURE_EXTRA,
                lens.getMax_aperture());
        return intent;
    }
}