package com.tbonas.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.tbonas.assignment2.model.Lens;
import com.tbonas.assignment2.model.LensManager;

/**
 * Add lens defined by user-input
 */
public class AddLensActivity extends AppCompatActivity {
    // Message to be output to user when trying to save their lens
    // Could be error or could be an acceptance message
    String message;

    // The user-input data
    String make_in;
    int focal_in;
    double aperture_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        setupFields();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_lens, menu);
        return true;
    }

    // In the AppBar when the user selects SAVE this will check
    // if input values are valid and output an error Toast and won't let the user save the
    // lens, otherwise it creates a lens and adds it to the singleton LensManager
    // and outputs an acceptance message
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_button:
                if (make_in == null || make_in.length() == 0) {
                    message = "Make of lens is empty";
                }
                else if (focal_in <= 0) {
                    message = "Focal length is less than or equal to 0";
                }
                else if (aperture_in < 1 || aperture_in > 22) {
                    message = "Aperture is out of range (1-22)";
                }
                else {
                    LensManager lenses = LensManager.getInstance();
                    lenses.add(new Lens(make_in, aperture_in, focal_in));
                    message = "Lens added";
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupFields() {
        EditText edit_make = (EditText)findViewById(R.id.make_field);
        edit_make.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                make_in = edit_make.getText().toString();
            }
        });
        EditText edit_focal = (EditText)findViewById(R.id.focal_length_field);
        edit_focal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = edit_focal.getText().toString();
                if (!"".equals(temp)) {
                    focal_in = Integer.parseInt(temp);
                }
            }
        });
        EditText edit_aperture = (EditText)findViewById(R.id.aperture_field);
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
            }
        });
    }
}