package com.tbonas.assignment2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tbonas.assignment2.model.Lens;
import com.tbonas.assignment2.model.LensManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.addbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LensManager lenses = LensManager.getInstance();

        // Default lenses
        lenses.add(new Lens("Canon", 1.8, 50));
        lenses.add(new Lens("Tamron", 2.8, 90));
        lenses.add(new Lens("Sigma", 2.8, 200));
        lenses.add(new Lens("Nikon", 4, 200));
        lenses.add(new Lens("El Cheepo", 12, 24));
        lenses.add(new Lens("Leica", 5.6, 1600));
        lenses.add(new Lens("The Wide", 1.0, 16));
        lenses.add(new Lens("I Wish", 1.0, 200));

        List<String> lens_list = new ArrayList<String>();

        SetupListView();

       // SetupAddBtn();
    }


    private void SetupListView() {
        LensManager lenses = LensManager.getInstance();
        List<String> lens_list = new ArrayList<String>();

        // Filling lens_list for output
        for (Lens lens:lenses) {
            lens_list.add(lens.getMake() + " " + lens.getFocal_length() +
                    "mm " + "F" + lens.getMax_aperture());
        }

        ListView list_view = (ListView)findViewById(R.id.listlensestxt);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, lens_list);
        list_view.setAdapter(adapter);


    }

    /*@Override
    private void SetupAddBtn() {
        Button btn = new
    } */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}