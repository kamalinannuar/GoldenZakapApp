package com.example.goldenzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar myToolbar;
    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnStart) {
            Intent intent = new Intent(this, CalculateActivity.class);
            startActivity(intent);

        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("type/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Visit my GitHub - https://github.com/kamalinannuar/GoldenZakapApp");
            startActivity(Intent.createChooser(shareIntent, null));

            return true;
        } else if (item.getItemId() == R.id.item_about) {
            Intent aboutIntent = new Intent (this, AboutActivity.class);
            startActivity(aboutIntent);

        }
        return false;

    }
}
