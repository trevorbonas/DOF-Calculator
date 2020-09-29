package com.tbonas.assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.tbonas.assignment2.model.DOFCalculator;
import com.tbonas.assignment2.model.Lens;

import java.text.DecimalFormat;

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

    private double coc_in;
    private double distance_in;
    private double aperture_in;


    private Lens picked_lens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        extractDataFromIntent();
        updateMakeLabel();

        setupInput();
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    private void calculate() {
        TextView n_focal_out = findViewById(R.id.n_focal_result_show);
        TextView f_focal_out = findViewById(R.id.f_focal_result_show);
        TextView dof_out = findViewById(R.id.dof_result_show);
        TextView hyperfocal_out = findViewById(R.id.hyper_result_show);

        double n_focal;
        double f_focal;
        double dof;
        double hyperfocal;

        Lens lens = new Lens(make, max_aperture, focal);

        if (coc_in <= 0) {
            n_focal_out.setText("Invalid COC");
            f_focal_out.setText("Invalid COC");
            dof_out.setText("Invalid COC");
            hyperfocal_out.setText("Invalid COC");
        }

        else if (aperture_in < lens.getMax_aperture() || aperture_in > 22) {
            n_focal_out.setText("Invalid aperture");
            f_focal_out.setText("Invalid aperture");
            dof_out.setText("Invalid aperture");
            hyperfocal_out.setText("Invalid aperture");
        }
        else if (distance_in < 0) {
            n_focal_out.setText("Invalid distance");
            f_focal_out.setText("Invalid distance");
            dof_out.setText("Invalid distance");
            hyperfocal_out.setText("Invalid aperture");
        }
        else {
            n_focal = DOFCalculator.calc_near(lens, distance_in, aperture_in, coc_in);
            f_focal = DOFCalculator.calc_far(lens, distance_in, aperture_in, coc_in);
            dof = DOFCalculator.dof(lens, distance_in, aperture_in, coc_in);
            hyperfocal = DOFCalculator.calc_hyperfocal(lens, aperture_in, coc_in);
            hyperfocal = hyperfocal/1000.0;

            n_focal_out.setText("" + formatM(n_focal) + "m");

            if (f_focal == Double.POSITIVE_INFINITY) {
                f_focal_out.setText("∞m");
            }
            else if (f_focal == Double.NEGATIVE_INFINITY) {
                f_focal_out.setText("-∞m");
            }
            else {
                f_focal_out.setText("" + formatM(f_focal) + "m");
            }

            if (f_focal == Double.POSITIVE_INFINITY) {
                dof_out.setText("∞m");
            }
            else if (f_focal == Double.NEGATIVE_INFINITY) {
                dof_out.setText("-∞m");
            }
            else {
                dof_out.setText("" + formatM(dof) + "m");;
            }

            hyperfocal_out.setText("" + formatM(hyperfocal) + "m");
        }

    }

    private void setupInput() {
        EditText edit_coc = (EditText)findViewById(R.id.coc_in_field);
        edit_coc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                coc_in = 0.029;

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = edit_coc.getText().toString();

                if (!"".equals(temp)) {
                    coc_in = Double.parseDouble(temp);
                }
                else {
                    coc_in = 0;
                }
                calculate();

            }
        });

        EditText edit_dist = (EditText)findViewById(R.id.distance_in_field);
        edit_dist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = edit_dist.getText().toString();

                if (!"".equals(temp)) {
                    distance_in = Double.parseDouble(temp);
                }
                calculate();
            }
        });

        EditText edit_aperture = (EditText)findViewById(R.id.s_aperture_in_field);
        edit_aperture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = edit_aperture.getText().toString();

                if (!"".equals(temp)) {
                    aperture_in = Double.parseDouble(temp);
                }
                calculate();
            }
        });

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